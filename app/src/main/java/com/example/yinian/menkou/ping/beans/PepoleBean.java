package com.example.yinian.menkou.ping.beans;

import java.util.List;

public class PepoleBean {


    /**
     * success : true
     * result : [{"id":289,"elderName":"赵子龙","elderSex":1,"elderBlood":"","region":"","address":"蜀地","createTime":1594259644000,"updateTime":1594785295000,"contractNumber":"00001","censusRegister":"非户籍","idCard":"0210000251541","bloodType":"RH","nationId":"哈萨克族","political":"团员","birthday":1120320000000,"telPhone":"1888888888","economySource":"城市低保","religiousBelief":"佛教","homeTel":"020455555","mediaChannels":"互联网","businessType":"农民","medicalInsurance":"城镇居民医疗保险","degreeEducation":2,"maritalStatus":1,"elderType":1,"formSupply":2,"retirementUnit":"康亦健","paymentMethod":2,"refereeName":"关羽","refereePay":2000,"allergicDrug":"","checkInTime":1594742400000,"inStatus":0,"nurseLevelId":4,"nurseLevelName":"介护1级","nurseGroupName":"","buildId":4,"buildName":"颐和","floorId":15,"floorName":"2F","roomId":8,"roomName":"201","bedId":22,"bedName":"02","orgId":2,"inDeposit":12000,"doctorId":13,"doctorName":"周云"},{"id":290,"elderName":"cesd","elderSex":1,"elderAge":6,"elderBlood":"","region":"","address":"","createTime":1594785342000,"updateTime":1594785444000,"contractNumber":"123123","censusRegister":"户籍","idCard":"124234234524","bloodType":"A","nationId":"汉族","political":"群众","telPhone":"14543434343","economySource":"养老保险","religiousBelief":"","homeTel":"","mediaChannels":"","businessType":"","medicalInsurance":"","retirementUnit":"","refereeName":"","allergicDrug":"","checkInTime":1594742400000,"inStatus":1,"nurseLevelId":5,"nurseLevelName":"介护Ⅱ级","nurseGroupName":"","buildId":4,"buildName":"颐和","floorId":15,"floorName":"2F","roomId":8,"roomName":"201","bedId":22,"bedName":"02","orgId":2,"inDeposit":13666,"doctorId":14,"doctorName":"宋坤华"}]
     * code : 1
     */

    private boolean success;
    private int code;
    private List<ResultDTO> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }

    public static class ResultDTO {
        /**
         * id : 289
         * elderName : 赵子龙
         * elderSex : 1
         * elderBlood :
         * region :
         * address : 蜀地
         * createTime : 1594259644000
         * updateTime : 1594785295000
         * contractNumber : 00001
         * censusRegister : 非户籍
         * idCard : 0210000251541
         * bloodType : RH
         * nationId : 哈萨克族
         * political : 团员
         * birthday : 1120320000000
         * telPhone : 1888888888
         * economySource : 城市低保
         * religiousBelief : 佛教
         * homeTel : 020455555
         * mediaChannels : 互联网
         * businessType : 农民
         * medicalInsurance : 城镇居民医疗保险
         * degreeEducation : 2
         * maritalStatus : 1
         * elderType : 1
         * formSupply : 2
         * retirementUnit : 康亦健
         * paymentMethod : 2
         * refereeName : 关羽
         * refereePay : 2000
         * allergicDrug :
         * checkInTime : 1594742400000
         * inStatus : 0
         * nurseLevelId : 4
         * nurseLevelName : 介护1级
         * nurseGroupName :
         * buildId : 4
         * buildName : 颐和
         * floorId : 15
         * floorName : 2F
         * roomId : 8
         * roomName : 201
         * bedId : 22
         * bedName : 02
         * orgId : 2
         * inDeposit : 12000
         * doctorId : 13
         * doctorName : 周云
         * elderAge : 6
         */

        private int id;
        private String elderName;
        private int elderSex;
        private String elderBlood;
        private String region;
        private String address;
        private long createTime;
        private long updateTime;
        private String contractNumber;
        private String censusRegister;
        private String idCard;
        private String bloodType;
        private String nationId;
        private String political;
        private long birthday;
        private String telPhone;
        private String economySource;
        private String religiousBelief;
        private String homeTel;
        private String mediaChannels;
        private String businessType;
        private String medicalInsurance;
        private int degreeEducation;
        private int maritalStatus;
        private int elderType;
        private int formSupply;
        private String retirementUnit;
        private int paymentMethod;
        private String refereeName;
        private int refereePay;
        private String allergicDrug;
        private long checkInTime;
        private int inStatus;
        private int nurseLevelId;
        private String nurseLevelName;
        private String nurseGroupName;
        private int buildId;
        private String buildName;
        private int floorId;
        private String floorName;
        private int roomId;
        private String roomName;
        private int bedId;
        private String bedName;
        private int orgId;
        private int inDeposit;
        private int doctorId;
        private String doctorName;
        private int elderAge;
        private String elderImage;

        public String getElderImage() {
            return elderImage;
        }

        public void setElderImage(String elderImage) {
            this.elderImage = elderImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getElderName() {
            return elderName;
        }

        public void setElderName(String elderName) {
            this.elderName = elderName;
        }

        public int getElderSex() {
            return elderSex;
        }

        public void setElderSex(int elderSex) {
            this.elderSex = elderSex;
        }

        public String getElderBlood() {
            return elderBlood;
        }

        public void setElderBlood(String elderBlood) {
            this.elderBlood = elderBlood;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getContractNumber() {
            return contractNumber;
        }

        public void setContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
        }

        public String getCensusRegister() {
            return censusRegister;
        }

        public void setCensusRegister(String censusRegister) {
            this.censusRegister = censusRegister;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getNationId() {
            return nationId;
        }

        public void setNationId(String nationId) {
            this.nationId = nationId;
        }

        public String getPolitical() {
            return political;
        }

        public void setPolitical(String political) {
            this.political = political;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getEconomySource() {
            return economySource;
        }

        public void setEconomySource(String economySource) {
            this.economySource = economySource;
        }

        public String getReligiousBelief() {
            return religiousBelief;
        }

        public void setReligiousBelief(String religiousBelief) {
            this.religiousBelief = religiousBelief;
        }

        public String getHomeTel() {
            return homeTel;
        }

        public void setHomeTel(String homeTel) {
            this.homeTel = homeTel;
        }

        public String getMediaChannels() {
            return mediaChannels;
        }

        public void setMediaChannels(String mediaChannels) {
            this.mediaChannels = mediaChannels;
        }

        public String getBusinessType() {
            return businessType;
        }

        public void setBusinessType(String businessType) {
            this.businessType = businessType;
        }

        public String getMedicalInsurance() {
            return medicalInsurance;
        }

        public void setMedicalInsurance(String medicalInsurance) {
            this.medicalInsurance = medicalInsurance;
        }

        public int getDegreeEducation() {
            return degreeEducation;
        }

        public void setDegreeEducation(int degreeEducation) {
            this.degreeEducation = degreeEducation;
        }

        public int getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(int maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public int getElderType() {
            return elderType;
        }

        public void setElderType(int elderType) {
            this.elderType = elderType;
        }

        public int getFormSupply() {
            return formSupply;
        }

        public void setFormSupply(int formSupply) {
            this.formSupply = formSupply;
        }

        public String getRetirementUnit() {
            return retirementUnit;
        }

        public void setRetirementUnit(String retirementUnit) {
            this.retirementUnit = retirementUnit;
        }

        public int getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(int paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getRefereeName() {
            return refereeName;
        }

        public void setRefereeName(String refereeName) {
            this.refereeName = refereeName;
        }

        public int getRefereePay() {
            return refereePay;
        }

        public void setRefereePay(int refereePay) {
            this.refereePay = refereePay;
        }

        public String getAllergicDrug() {
            return allergicDrug;
        }

        public void setAllergicDrug(String allergicDrug) {
            this.allergicDrug = allergicDrug;
        }

        public long getCheckInTime() {
            return checkInTime;
        }

        public void setCheckInTime(long checkInTime) {
            this.checkInTime = checkInTime;
        }

        public int getInStatus() {
            return inStatus;
        }

        public void setInStatus(int inStatus) {
            this.inStatus = inStatus;
        }

        public int getNurseLevelId() {
            return nurseLevelId;
        }

        public void setNurseLevelId(int nurseLevelId) {
            this.nurseLevelId = nurseLevelId;
        }

        public String getNurseLevelName() {
            return nurseLevelName;
        }

        public void setNurseLevelName(String nurseLevelName) {
            this.nurseLevelName = nurseLevelName;
        }

        public String getNurseGroupName() {
            return nurseGroupName;
        }

        public void setNurseGroupName(String nurseGroupName) {
            this.nurseGroupName = nurseGroupName;
        }

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public int getBedId() {
            return bedId;
        }

        public void setBedId(int bedId) {
            this.bedId = bedId;
        }

        public String getBedName() {
            return bedName;
        }

        public void setBedName(String bedName) {
            this.bedName = bedName;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getInDeposit() {
            return inDeposit;
        }

        public void setInDeposit(int inDeposit) {
            this.inDeposit = inDeposit;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public int getElderAge() {
            return elderAge;
        }

        public void setElderAge(int elderAge) {
            this.elderAge = elderAge;
        }
    }
}
