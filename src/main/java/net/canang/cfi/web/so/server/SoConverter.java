package net.canang.cfi.web.so.server;

import net.canang.cfi.biz.so.manager.SoFinder;
import net.canang.cfi.biz.so.manager.SoManager;
import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.model.*;
import net.canang.cfi.web.dd.server.DdConverter;
import net.canang.cfi.web.so.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Component("soConverter")
public class SoConverter {

    @Autowired
    private SoManager soManager;

    @Autowired
    private SoFinder soFinder;

    @Autowired
    private DdConverter ddConverter;

    public UserModel convert(CfUser user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setEmail(user.getEmail());
        model.setUsername(user.getName());
        model.setPassword(user.getPassword());
        model.setName(user.getName());
        model.setRealname(user.getRealname());
        if (null != user.getActor()) {
            CfActor actor = user.getActor();
            // todo summary
        }
        return model;
    }

    public GroupModel convert(CfGroup group) {
        GroupModel model = new GroupModel();
        model.setId(group.getId());
        model.setName(group.getName());
        model.setSummary(group.getName());
        return model;
    }

    public PrincipalModel convert(CfPrincipal principal) {
        PrincipalModel model = null;
        switch (principal.getPrincipalType()) {
            case USER:
                model = convert((CfUser) principal);
                break;
            case GROUP:
                model = convert((CfGroup) principal);
                break;
        }
        return model;
    }

    public ReferenceNoModel convert(CfReferenceNo referenceNo) {
        ReferenceNoModel model = new ReferenceNoModel();
        model.setId(referenceNo.getId());
        model.setCode(referenceNo.getCode());
        model.setDescription(referenceNo.getDescription());
        model.setPrefix(referenceNo.getPrefix());
        model.setSequenceFormat(referenceNo.getSequenceFormat());
        model.setReferenceFormat(referenceNo.getReferenceFormat());
        model.setIncrementValue(referenceNo.getIncrementValue());
        model.setCurrentValue(referenceNo.getCurrentValue());
        return model;
    }

    public ConfigurationModel convert(CfConfiguration configuration) {
        ConfigurationModel model = new ConfigurationModel();
        model.setId(configuration.getId());
        model.setKey(configuration.getKey());
        model.setValue(configuration.getValue());
        model.setDescription(configuration.getDescription());
        return model;
    }

//
//    public VendorModel transform(CfVendor vendor) {
//        if (null != vendor) {
//            VendorModel model = (VendorModel) transform(vendor, CfActorType.VENDOR);
//            model.setVendorNo(vendor.getVendorNo());
//            int val = vendor.isBumiputera() ? 0 : 1;
//            model.setBumiputra(new SimpleModel(YesNoType.values()[val].getId(), YesNoType.values()[val].getDesc()));
//            model.setTaxNo(vendor.getTaxNo());
//
//            model.setServiceCertNo(vendor.getServiceCertNo());
//            model.setServiceStartDate(vendor.getServiceStartDate());
//            model.setServiceEndDate(vendor.getServiceEndDate());
//
//            model.setWorkCertNo(vendor.getWorkCertNo());
//            model.setWorkStartDate(vendor.getWorkStartDate());
//            model.setWorkEndDate(vendor.getWorkEndDate());
//
//            model.setSsmStartDate(vendor.getSsmStartDate());
//            model.setSsmEndDate(vendor.getSsmEndDate());
//
//            model.setFieldCertNo(vendor.getFieldCertNo());
//            model.setFieldStartDate(vendor.getFieldStartDate());
//            model.setFieldEndDate(vendor.getFieldEndDate());
//
//            model.setPkkCertNo(vendor.getPkkCertNo());
//            model.setPkkStartDate(vendor.getPkkStartDate());
//            model.setPkkEndDate(vendor.getPkkEndDate());
//
//            model.setCidbCertNo(vendor.getCidbCertNo());
//            model.setCidbStartDate(vendor.getCidbStartDate());
//            model.setCidbEndDate(vendor.getCidbEndDate());
//
//            model.setAddressModel(transform(vendor.getAddressInfo()));
//            model.setSummaryFieldCodes(vendor.getSummaryFieldCodes());
//
//            model.setCreditRating(vendor.getCreditRating());
//            if (null != model.getCreditTerm())
//                model.setCreditTerm(new SimpleModel(vendor.getCreditTerm().ordinal(), vendor.getCreditTerm().name()));
//            if (null != model.getPaymentMethod()) model.setPaymentMethod(
//                    new SimpleModel(vendor.getPaymentMethod().ordinal(), vendor.getPaymentMethod().getDescription()));
//            model.setSummary(vendor.getSummary());
//            return model;
//        } else return null;
//    }
//
//    public StaffModel transform(CfStaff staff) {
//        if (null != staff) return (StaffModel) transform(staff, STAFF);
//        else return null;
//    }
//
//    public ActorModel transform(CfActor consumer) {
//        return transform(consumer, consumer.getActorType());
//    }
//
//    public ActorModel transform(CfActor consumer, CfActorType consumerType) {
//        ActorModel model = null;
//        try {
//
//            // consumer properties
//            model = getActorModel(consumerType);
//            model.setId(consumer.getId());
//            model.setCode(consumer.getCode());
//            model.setIdentityNo(consumer.getIdentityNo());
//            model.setName(consumer.getName());
//            model.setEmail(consumer.getEmail());
//            model.setPhone(consumer.getPhone());
//            model.setExtension(consumer.getExtension());
//            model.setFax(consumer.getFax());
//            model.setSummary(consumer.getSummary());
//
//            // bank and address
//            if (null != consumer.getBank()) model.setBank(transform(consumer.getBank()));
//            if (null != consumer.getBankAccount()) model.setBankAccount(consumer.getBankAccount());
//            if (null != consumer.getAddressInfo()) model.setAddressModel(transform(consumer.getAddressInfo()));
//            if (null != consumer.getSummaryAddress()) model.setSummaryAddress(consumer.getSummaryAddress());
//
//            // blacklist
//            int val = consumer.isBlackListed() ? 0 : 1;
//            model.setBlackListed(new SimpleModel(YesNoType.values()[val].getId(), YesNoType.values()[val].getDesc()));
//            model.setBlacklistedStartDate(consumer.getBlackListedStartDate());
//            model.setBlacklistedEndDate(consumer.getBlackListedEndDate());
//
//            // custom properties for staff
//            if (STAFF.equals(consumer.getActorType())) {
//                ((StaffModel) model).setGender(Gender.get(((CfStaff) consumer).getGender().ordinal()));
//
//                if (null != ((CfStaff) consumer).getPositionCode()) {
//                    ((StaffModel) model).setHourlyRate(((CfStaff) consumer).getHourlyRate());
//                    ((StaffModel) model).setSalary(((CfStaff) consumer).getSalary());
//                    ((StaffModel) model).setPosition(transform(((CfStaff) consumer).getPositionCode()));
//                    ((StaffModel) model).setPositionCode(((CfStaff) consumer).getPositionCode().getCode());
//                    ((StaffModel) model).setStaffKelas(
//                            ((CfStaff) consumer).getPositionCode().getGradeClass().getClazz());
//                    ((StaffModel) model).setGradeCode(((CfStaff) consumer).getPositionCode().getGradeClass().getName());
//
//                    if (null != ((CfStaff) consumer).getPayrollPaymentMethod()) {
//                        PaymentMethodType paymentMethodType =
//                                PaymentMethodType.values()[((CfStaff) consumer).getPayrollPaymentMethod().ordinal()];
//                        ((StaffModel) model).setPayrollPaymentMethod(
//                                new SimpleModel(paymentMethodType.getId(), paymentMethodType.getDesc()));
//                    }
//                    ((StaffModel) model).setCitizen(((CfStaff) consumer).isCitizen());
//                    ((StaffModel) model).setCivilian(((CfStaff) consumer).isCivilian());
//                    ((StaffModel) model).setAssumed(((CfStaff) consumer).isAssumed());
//                    ((StaffModel) model).setSuspended(((CfStaff) consumer).isSuspended());
//                    ((StaffModel) model).setInterimed(((CfStaff) consumer).isInterimed());
//
//                    ((StaffModel) model).setEpfActive(((CfStaff) consumer).isEpfActive());
//                    ((StaffModel) model).setSocsoActive(((CfStaff) consumer).isSocsoActive());
//                    ((StaffModel) model).setPcbActive(((CfStaff) consumer).isPcbActive());
//                    ((StaffModel) model).setKwapActive(((CfStaff) consumer).isKwapActive());
//
//                    ((StaffModel) model).setNricNo(((CfStaff) consumer).getNricNo());
//                    ((StaffModel) model).setArpoNo(((CfStaff) consumer).getArpoNo());
//                    ((StaffModel) model).setAsbNo(((CfStaff) consumer).getAsbNo());
//                    ((StaffModel) model).setCoopNo(((CfStaff) consumer).getCoopNo());
//                    ((StaffModel) model).setThNo(((CfStaff) consumer).getThNo());
//                    ((StaffModel) model).setBpp1No(((CfStaff) consumer).getBpp1No());
//                    ((StaffModel) model).setBpp2No(((CfStaff) consumer).getBpp2No());
//                    ((StaffModel) model).setEpfNo(((CfStaff) consumer).getEpfNo());
//                    ((StaffModel) model).setSocsoNo(((CfStaff) consumer).getSocsoNo());
//                    ((StaffModel) model).setKwapNo(((CfStaff) consumer).getKwapNo());
//                    ((StaffModel) model).setFileNo(((CfStaff) consumer).getFileNo());
//                    ((StaffModel) model).setTaxNo(((CfStaff) consumer).getTaxNo());
//
//                    ((StaffModel) model).setQualification(((CfStaff) consumer).isQualified());
//                    ((StaffModel) model).setStartDate(((CfStaff) consumer).getStartDate());
//                    ((StaffModel) model).setEndDate(((CfStaff) consumer).getEndDate());
//                    ((StaffModel) model).setServicePeriod(((CfStaff) consumer).getServicePeriod());
//
//                    // payroll
//                    CfCostCenter payrollCc = ((CfStaff) consumer).getPayrollCostCenter();
//                    if (null != payrollCc) ((StaffModel) model).setPayrollCostCenter(transform(payrollCc));
//                }
//
//                // custom properties for person
//            } else if (PERSON.equals(consumer.getActorType())) {
//                if (null != ((CfPerson) consumer).getPositionCode()) {
//                    ((PersonModel) model).setSalary(((CfPerson) consumer).getSalary());
//                    ((PersonModel) model).setPositionCode(((CfPerson) consumer).getPositionCode().getCode());
//                    ((PersonModel) model).setPositionCodeModel(transform(((CfPerson) consumer).getPositionCode()));
//                    ((PersonModel) model).setStaffKelas(
//                            ((CfPerson) consumer).getPositionCode().getGradeClass().getClazz());
//                    ((PersonModel) model).setStaffGred(((CfPerson) consumer).getPositionCode().getGrade());
//                }
//            }
//        } catch (Exception e) {
//            log.error("error occured", e);
//        }
//        return model;
//    }
//
//    private ActorModel getActorModel(CfActorType type) {
//        ActorModel model = null;
//        if (null == type) return new ActorModel();
//        switch (type) {
//            case STAFF:
//                model = new StaffModel();
//                break;
//            case VENDOR:
//                model = new VendorModel();
//                break;
//            case PERSON:
//                model = new PersonModel();
//                break;
//            case AGENCY:
//                model = new AgencyModel();
//                break;
//        }
//        return model;
//    }

}
