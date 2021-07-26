package test.design_mode.WaiGuan;

/**
 * Created by ncx on 2021/6/11
 * 外观类
 */
public class ZhangWuJi {
    private NeiGong neiGong;
    private ZhaoShi zhaoShi;
    private JingMai jingMai;

    public ZhangWuJi() {
        neiGong = new NeiGong();
        zhaoShi = new ZhaoShi();
        jingMai = new JingMai();
    }

    public void qianKun() {
        //组合乾坤大挪移
        jingMai.jingmai();
        neiGong.QianKun();
    }

    public void qiShang() {
        //组合七伤拳
        jingMai.jingmai();
        neiGong.JiuYang();
        zhaoShi.QiShangQuan();
    }
}
