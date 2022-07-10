package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/09/21:09
 * @Description:
 */
public class AgvStateInfo {
   private String agvid;
   private String SendTime;
   private String power_on_time;
   private String mode_running;
   private String path_running;
   private float battery;
   private float theta	;
   private float xpos	;
   private float ypos	;
   private float cur_cap;
   private float exec_cap;
   private float target_cap;
   private String exec_route;
   private float manual_auto;
   private boolean robot_ready;
   private boolean task_complete;
   private boolean task_stopping;
    private boolean radar_error;
    private boolean radar_disconnect;
    private boolean motor_error;
    private boolean motor_disconnect;
    private boolean board_disconnect;
    private boolean imu_disconnect;
    private float warning_code;
    private float error_code;
    private float total_odom;
    private float today_odom;
    private String cur_map;
    private String vel_line;
    private float vel_angle;
    private float line_enc;
    private float external_sts;
    private float water_level;
    private float alcohol_level;
    private float arm_arrive;
    private float arm_work_finish;
    private float left_laser_dist;
    private float right_laser_dist;
    private float robot_moving;
    private float cap_control;
    private float lamp_switch;
    private float atomizer_switch;
    private float fan_switch;
    private float water_pump_switch;
    private float alcohol_pump_switch;

    public String getAgvid() {
        return agvid;
    }

    public void setAgvid(String agvid) {
        this.agvid = agvid;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public String getPower_on_time() {
        return power_on_time;
    }

    public void setPower_on_time(String power_on_time) {
        this.power_on_time = power_on_time;
    }

    public String getMode_running() {
        return mode_running;
    }

    public void setMode_running(String mode_running) {
        this.mode_running = mode_running;
    }

    public String getPath_running() {
        return path_running;
    }

    public void setPath_running(String path_running) {
        this.path_running = path_running;
    }

    public float getBattery() {
        return battery;
    }

    public void setBattery(float battery) {
        this.battery = battery;
    }

    public float getTheta() {
        return theta;
    }

    public void setTheta(float theta) {
        this.theta = theta;
    }

    public float getXpos() {
        return xpos;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    public float getCur_cap() {
        return cur_cap;
    }

    public void setCur_cap(float cur_cap) {
        this.cur_cap = cur_cap;
    }

    public float getExec_cap() {
        return exec_cap;
    }

    public void setExec_cap(float exec_cap) {
        this.exec_cap = exec_cap;
    }

    public float getTarget_cap() {
        return target_cap;
    }

    public void setTarget_cap(float target_cap) {
        this.target_cap = target_cap;
    }

    public String getExec_route() {
        return exec_route;
    }

    public void setExec_route(String exec_route) {
        this.exec_route = exec_route;
    }

    public float getManual_auto() {
        return manual_auto;
    }

    public void setManual_auto(float manual_auto) {
        this.manual_auto = manual_auto;
    }

    public boolean isRobot_ready() {
        return robot_ready;
    }

    public void setRobot_ready(boolean robot_ready) {
        this.robot_ready = robot_ready;
    }

    public boolean isTask_complete() {
        return task_complete;
    }

    public void setTask_complete(boolean task_complete) {
        this.task_complete = task_complete;
    }

    public boolean isTask_stopping() {
        return task_stopping;
    }

    public void setTask_stopping(boolean task_stopping) {
        this.task_stopping = task_stopping;
    }

    public boolean isRadar_error() {
        return radar_error;
    }

    public void setRadar_error(boolean radar_error) {
        this.radar_error = radar_error;
    }

    public boolean isRadar_disconnect() {
        return radar_disconnect;
    }

    public void setRadar_disconnect(boolean radar_disconnect) {
        this.radar_disconnect = radar_disconnect;
    }

    public boolean isMotor_error() {
        return motor_error;
    }

    public void setMotor_error(boolean motor_error) {
        this.motor_error = motor_error;
    }

    public boolean isMotor_disconnect() {
        return motor_disconnect;
    }

    public void setMotor_disconnect(boolean motor_disconnect) {
        this.motor_disconnect = motor_disconnect;
    }

    public boolean isBoard_disconnect() {
        return board_disconnect;
    }

    public void setBoard_disconnect(boolean board_disconnect) {
        this.board_disconnect = board_disconnect;
    }

    public boolean isImu_disconnect() {
        return imu_disconnect;
    }

    public void setImu_disconnect(boolean imu_disconnect) {
        this.imu_disconnect = imu_disconnect;
    }

    public float getWarning_code() {
        return warning_code;
    }

    public void setWarning_code(float warning_code) {
        this.warning_code = warning_code;
    }

    public float getError_code() {
        return error_code;
    }

    public void setError_code(float error_code) {
        this.error_code = error_code;
    }

    public float getTotal_odom() {
        return total_odom;
    }

    public void setTotal_odom(float total_odom) {
        this.total_odom = total_odom;
    }

    public float getToday_odom() {
        return today_odom;
    }

    public void setToday_odom(float today_odom) {
        this.today_odom = today_odom;
    }

    public String getCur_map() {
        return cur_map;
    }

    public void setCur_map(String cur_map) {
        this.cur_map = cur_map;
    }

    public String getVel_line() {
        return vel_line;
    }

    public void setVel_line(String vel_line) {
        this.vel_line = vel_line;
    }

    public float getVel_angle() {
        return vel_angle;
    }

    public void setVel_angle(float vel_angle) {
        this.vel_angle = vel_angle;
    }

    public float getLine_enc() {
        return line_enc;
    }

    public void setLine_enc(float line_enc) {
        this.line_enc = line_enc;
    }

    public float getExternal_sts() {
        return external_sts;
    }

    public void setExternal_sts(float external_sts) {
        this.external_sts = external_sts;
    }

    public float getWater_level() {
        return water_level;
    }

    public void setWater_level(float water_level) {
        this.water_level = water_level;
    }

    public float getAlcohol_level() {
        return alcohol_level;
    }

    public void setAlcohol_level(float alcohol_level) {
        this.alcohol_level = alcohol_level;
    }

    public float getArm_arrive() {
        return arm_arrive;
    }

    public void setArm_arrive(float arm_arrive) {
        this.arm_arrive = arm_arrive;
    }

    public float getArm_work_finish() {
        return arm_work_finish;
    }

    public void setArm_work_finish(float arm_work_finish) {
        this.arm_work_finish = arm_work_finish;
    }

    public float getLeft_laser_dist() {
        return left_laser_dist;
    }

    public void setLeft_laser_dist(float left_laser_dist) {
        this.left_laser_dist = left_laser_dist;
    }

    public float getRight_laser_dist() {
        return right_laser_dist;
    }

    public void setRight_laser_dist(float right_laser_dist) {
        this.right_laser_dist = right_laser_dist;
    }

    public float getRobot_moving() {
        return robot_moving;
    }

    public void setRobot_moving(float robot_moving) {
        this.robot_moving = robot_moving;
    }

    public float getCap_control() {
        return cap_control;
    }

    public void setCap_control(float cap_control) {
        this.cap_control = cap_control;
    }

    public float getLamp_switch() {
        return lamp_switch;
    }

    public void setLamp_switch(float lamp_switch) {
        this.lamp_switch = lamp_switch;
    }

    public float getAtomizer_switch() {
        return atomizer_switch;
    }

    public void setAtomizer_switch(float atomizer_switch) {
        this.atomizer_switch = atomizer_switch;
    }

    public float getFan_switch() {
        return fan_switch;
    }

    public void setFan_switch(float fan_switch) {
        this.fan_switch = fan_switch;
    }

    public float getWater_pump_switch() {
        return water_pump_switch;
    }

    public void setWater_pump_switch(float water_pump_switch) {
        this.water_pump_switch = water_pump_switch;
    }

    public float getAlcohol_pump_switch() {
        return alcohol_pump_switch;
    }

    public void setAlcohol_pump_switch(float alcohol_pump_switch) {
        this.alcohol_pump_switch = alcohol_pump_switch;
    }
}
