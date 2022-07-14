package com.server.emcloud.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/14/3:26
 * @Description:
 */
public class ExceptionCode {

    public String getException(int code,String errortype)
    {
        String des = "无故障";

        if (errortype=="error")
        {
            switch((int) code)
            {
                case 1:
                    des = "故障-急停";
                    break;
                case 2:
                    des = "激光避障";
                    break;
                case 3:
                    des = "点激光避障";
                    break;
                case 4:
                    des = "故障-防撞条碰撞";
                    break;
                case 5:
                    des = "故障-电机故障";
                    break;
                case 6:
                    des = "故障-暂停";
                    break;
                case 18:
                    des = "故障-左侧障碍触发";
                    break;
                case 19:
                    des = "故障-右侧障碍触发";
                    break;
                case 20:
                    des = "故障-后避障停车";
                    break;
                case 21:
                    des = "二维码读数报错";
                    break;
                case 22:
                    des = " 叉齿碰撞 ";
                    break;
                case 23:
                    des = "O3D相机识别失败";
                    break;
            }
        }
        else
        {
            switch (code)
            {
                case 1:
                    des = "告警-避障停车";
                    break;
                case 2:
                    des = "告警-漏点";
                    break;
                case 3:
                    des = "告警-物料检测故障";
                    break;
                case 4:
                    des = "告警-低电量";
                    break;
                case 5:
                    des = "告警-侧面避障停车";
                    break;
                case 8:
                    des = "告警-前后避障停车";
                    break;
                case 9:
                    des = "告警-激光避障停车";
                    break;
                case 10:
                    des = "告警-机器人定位未完成";
                    break;
                case 21:
                    des = "控制板未连接";
                    break;
                case 22:
                    des = "激光未连接";
                    break;
                case 23:
                    des = " 二维码未连接 ";
                    break;
                case 24:
                    des = "IMU未连接";
                    break;
                case 25:
                    des = "左激光避障";
                    break;
                case 26:
                    des = "右激光避障";
                    break;
                case 27:
                    des = "二维码未矫正";
                    break;
            }
        }
        return des;
    }

}
