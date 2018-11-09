package com.tian.spring;

/**
 * @author tianbeiping
 * @Title: redis
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午5:03
 */
public class redis {


    private int shopId = 121231;
    private String key_0 = "shop:config:" + shopId + ":shop_order_config";
    //订单配置表
    public class ShopOrderConfig {
        private String usrAmStart;
        private String usrAmEnd;
        private byte queueState;
        private byte tableConfEnable;
        private byte serviceFee;
        private byte businessFree;
        private byte offInvalid;
        private byte noneAble;
        private byte delayPass;
    }

    private String key_1 = "shop:config:" + shopId + ":shop_show_config";
    //展示配置表
    public class ShopShowConfig {
        private byte hasSmsTemp;
        private byte showIconLabel;
        private byte takePushAdvertise;
        private byte openQuick;
    }


    private String key_2 = "shop:config:" + shopId + ":shop_mall_config";
    //商城配置表
    public class ShopMallConfig {
        private byte tryService;
        private byte openAllService;
    }


    private String key_4 = "shop:config:" + shopId + ":shop_tranform_config";
    //流转配置表
    public class ShopTranformConfig {
        private byte sendSms;
        private byte waitRedEnvelope;
        private byte takeOrderPush;
        private byte estimateTime;
        private byte waitDiscounts;
    }

    private String key_5 = "shop:config:" + shopId + ":shop_permission_config";
    //权限配置表
    public class ShopPermissionConfig {
        private byte queueLimit;
        private byte checkSn;
    }

    private String key_6 = "shop:config:" + shopId + ":shop_mall_config";
    //其他配置表
    public class ShopOtherConfig {
        private byte assistEnable;
    }

}
