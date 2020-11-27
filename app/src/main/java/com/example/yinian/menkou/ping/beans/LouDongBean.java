package com.example.yinian.menkou.ping.beans;

import java.util.List;

public class LouDongBean {


    /**
     * success : true
     * result : [{"id":4,"buildName":"颐和","buildCode":"101","remark":"护理住院","createTime":1592706791000,"floors":[{"id":14,"floorName":"1F","floorCode":"1F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[{"id":6,"roomName":"101","roomLevel":2,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1592707230000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":4},{"id":14,"roomName":"102","roomLevel":4,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1598435018000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":1}]},{"id":15,"floorName":"2F","floorCode":"2F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[{"id":8,"roomName":"201","roomLevel":4,"orientation":"朝北","roomLighted":4,"roomAirness":4,"createTime":1593573006000,"buildId":4,"buildCode":"101","floorId":15,"orgId":2,"bedNum":2}]},{"id":16,"floorName":"3F","floorCode":"3F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]},{"id":17,"floorName":"4F","floorCode":"4F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]},{"id":18,"floorName":"5F","floorCode":"5F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]}]},{"id":5,"buildName":"康宁","buildCode":"1002","remark":"","createTime":1592873288000,"floors":[{"id":23,"floorName":"1F","floorCode":"1F","createTime":1593499925000,"buildCode":"1002","buildId":5,"roomList":[{"id":7,"roomName":"101","roomLevel":4,"orientation":"朝北","roomLighted":4,"roomAirness":4,"createTime":1593499957000,"buildId":5,"buildCode":"1002","floorId":23,"bedNum":4}]}]}]
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
         * id : 4
         * buildName : 颐和
         * buildCode : 101
         * remark : 护理住院
         * createTime : 1592706791000
         * floors : [{"id":14,"floorName":"1F","floorCode":"1F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[{"id":6,"roomName":"101","roomLevel":2,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1592707230000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":4},{"id":14,"roomName":"102","roomLevel":4,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1598435018000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":1}]},{"id":15,"floorName":"2F","floorCode":"2F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[{"id":8,"roomName":"201","roomLevel":4,"orientation":"朝北","roomLighted":4,"roomAirness":4,"createTime":1593573006000,"buildId":4,"buildCode":"101","floorId":15,"orgId":2,"bedNum":2}]},{"id":16,"floorName":"3F","floorCode":"3F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]},{"id":17,"floorName":"4F","floorCode":"4F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]},{"id":18,"floorName":"5F","floorCode":"5F","createTime":1592706803000,"buildCode":"101","buildId":4,"orgId":2,"roomList":[]}]
         */

        private int id;
        private String buildName;
        private String buildCode;
        private String remark;
        private long createTime;
        private List<FloorsDTO> floors;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getBuildCode() {
            return buildCode;
        }

        public void setBuildCode(String buildCode) {
            this.buildCode = buildCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public List<FloorsDTO> getFloors() {
            return floors;
        }

        public void setFloors(List<FloorsDTO> floors) {
            this.floors = floors;
        }

        public static class FloorsDTO {
            /**
             * id : 14
             * floorName : 1F
             * floorCode : 1F
             * createTime : 1592706803000
             * buildCode : 101
             * buildId : 4
             * orgId : 2
             * roomList : [{"id":6,"roomName":"101","roomLevel":2,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1592707230000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":4},{"id":14,"roomName":"102","roomLevel":4,"orientation":"朝东","roomLighted":1,"roomAirness":1,"createTime":1598435018000,"buildId":4,"buildCode":"101","floorId":14,"orgId":2,"bedNum":1}]
             */

            private int id;
            private String floorName;
            private String floorCode;
            private long createTime;
            private String buildCode;
            private int buildId;
            private int orgId;
            private List<RoomListDTO> roomList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFloorName() {
                return floorName;
            }

            public void setFloorName(String floorName) {
                this.floorName = floorName;
            }

            public String getFloorCode() {
                return floorCode;
            }

            public void setFloorCode(String floorCode) {
                this.floorCode = floorCode;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getBuildCode() {
                return buildCode;
            }

            public void setBuildCode(String buildCode) {
                this.buildCode = buildCode;
            }

            public int getBuildId() {
                return buildId;
            }

            public void setBuildId(int buildId) {
                this.buildId = buildId;
            }

            public int getOrgId() {
                return orgId;
            }

            public void setOrgId(int orgId) {
                this.orgId = orgId;
            }

            public List<RoomListDTO> getRoomList() {
                return roomList;
            }

            public void setRoomList(List<RoomListDTO> roomList) {
                this.roomList = roomList;
            }

            public static class RoomListDTO {
                /**
                 * id : 6
                 * roomName : 101
                 * roomLevel : 2
                 * orientation : 朝东
                 * roomLighted : 1
                 * roomAirness : 1
                 * createTime : 1592707230000
                 * buildId : 4
                 * buildCode : 101
                 * floorId : 14
                 * orgId : 2
                 * bedNum : 4
                 */

                private int id;
                private String roomName;
                private int roomLevel;
                private String orientation;
                private int roomLighted;
                private int roomAirness;
                private long createTime;
                private int buildId;
                private String buildCode;
                private int floorId;
                private int orgId;
                private int bedNum;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getRoomName() {
                    return roomName;
                }

                public void setRoomName(String roomName) {
                    this.roomName = roomName;
                }

                public int getRoomLevel() {
                    return roomLevel;
                }

                public void setRoomLevel(int roomLevel) {
                    this.roomLevel = roomLevel;
                }

                public String getOrientation() {
                    return orientation;
                }

                public void setOrientation(String orientation) {
                    this.orientation = orientation;
                }

                public int getRoomLighted() {
                    return roomLighted;
                }

                public void setRoomLighted(int roomLighted) {
                    this.roomLighted = roomLighted;
                }

                public int getRoomAirness() {
                    return roomAirness;
                }

                public void setRoomAirness(int roomAirness) {
                    this.roomAirness = roomAirness;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getBuildId() {
                    return buildId;
                }

                public void setBuildId(int buildId) {
                    this.buildId = buildId;
                }

                public String getBuildCode() {
                    return buildCode;
                }

                public void setBuildCode(String buildCode) {
                    this.buildCode = buildCode;
                }

                public int getFloorId() {
                    return floorId;
                }

                public void setFloorId(int floorId) {
                    this.floorId = floorId;
                }

                public int getOrgId() {
                    return orgId;
                }

                public void setOrgId(int orgId) {
                    this.orgId = orgId;
                }

                public int getBedNum() {
                    return bedNum;
                }

                public void setBedNum(int bedNum) {
                    this.bedNum = bedNum;
                }
            }
        }
    }
}
