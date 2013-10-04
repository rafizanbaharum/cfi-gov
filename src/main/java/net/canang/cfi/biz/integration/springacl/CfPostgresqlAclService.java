package net.canang.cfi.biz.integration.springacl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.*;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
public class CfPostgresqlAclService implements AclService {
	// ~ Static fields/initializers
	// =====================================================================================

	protected static final Log log = LogFactory.getLog(CfPostgresqlAclService.class);
	private static final String selectAclObjectWithParent = "SELECT obj.object_id_identity AS obj_id, "
			+ "       class.class AS class " + "  FROM acl_object_identity obj, "
			+ "       acl_object_identity parent, " + "       acl_class class "
			+ " WHERE obj.parent_object = parent.id " + "   AND obj.object_id_class = class.id "
			+ "   AND parent.object_id_identity = ? "
			+ "   AND parent.object_id_class = (SELECT id FROM acl_class WHERE acl_class.class = ?)";

	// ~ Instance fields
	// ================================================================================================

	protected JdbcTemplate jdbcTemplate;
	private LookupStrategy lookupStrategy;

	// ~ Constructors
	// ===================================================================================================

	public CfPostgresqlAclService(DataSource dataSource, LookupStrategy lookupStrategy) {
		Assert.notNull(dataSource, "DataSource required");
		Assert.notNull(lookupStrategy, "LookupStrategy required");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.lookupStrategy = lookupStrategy;
	}

	// ~ Methods
	// ========================================================================================================

	public List<ObjectIdentity> findChildren(ObjectIdentity parentIdentity) {
		Object[] args = { parentIdentity.getIdentifier(), parentIdentity.getType()};
		List objects = jdbcTemplate.query(selectAclObjectWithParent, args, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String javaType = rs.getString("class");
				String identifier = rs.getString("obj_id");

				return new ObjectIdentityImpl(javaType, identifier);
			}
		});
		return objects;
	}

	public Acl readAclById(ObjectIdentity object, List<Sid> sids) throws NotFoundException {
		Map map = readAclsById(Arrays.asList(object), sids);

		if (map.size() == 0) {
			throw new NotFoundException("Could not find ACL");
		} else {
			return (Acl) map.get(object);
		}
	}

	public Acl readAclById(ObjectIdentity object) throws NotFoundException {
		return readAclById(object);
	}

	public Map readAclsById(List<ObjectIdentity> objects) {
		return readAclsById(objects, null);
	}

	public Map readAclsById(List<ObjectIdentity> objects, List<Sid> sids) throws NotFoundException {
		return lookupStrategy.readAclsById(objects, sids);
	}
}
