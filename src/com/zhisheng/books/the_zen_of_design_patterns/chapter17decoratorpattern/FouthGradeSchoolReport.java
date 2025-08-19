package com.zhisheng.books.the_zen_of_design_patterns.chapter17decoratorpattern;

/**
 * @ClassName FouthGradeSchoolReport
 * @Description 四年级成绩单
 * @Author pengzhisheng
 * @Date 2020/7/20 23:39
 * @Version 1.0
 */
public class FouthGradeSchoolReport extends SchoolReport {
    // 成绩单
    @Override
    public void report() {
        System.out.println("尊敬的xxx家长： ");
        System.out.println(" 。。。。。。。");
        System.out.println(" 语文 62，数学 78，体育 95，自然 73");
        System.out.println(" 。。。。。。。");
        System.out.println("          家长签名：       ");
    }

    // 家长签字
    @Override
    public void sign(String name) {
        System.out.println("家长签名为：" + name);
    }
}
