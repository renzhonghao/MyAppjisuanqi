package com.example.administrator.myappjisuanqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add;     //按钮+
    private Button subtract;    //按钮-
    private Button multiply;    //按钮*
    private Button divide;      //按钮/
    private Button equal;       //按钮=
    private Button delete;      //按钮删除一个输入的符号
    private Button clear;       //按钮用来清除输入框
    private Button point;       //按钮小数点
    private Button change;      //按钮进制转换

    private Button one;         //按钮1 以下类推
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;

    private EditText showtext;//用来显示输入的符号和数字
    private String OperateSum = "";//字符串用来保存输入的数字和符号
    private char[] Operator;      //用来记录运算符号

    private double num1 = 0, num2 = 0, sum = 0, flag = 0;     //num1,num2记录输入的数字，sum保存运算的结果
    private int i = 0, j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//对按钮和事件进行初始化
    }

    private void initView() {
        Operator = new char[50];
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);

        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        delete = (Button) findViewById(R.id.delete);
        clear = (Button) findViewById(R.id.clear);
        equal = (Button) findViewById(R.id.equal);
        point = (Button) findViewById(R.id.point);
        change = (Button) findViewById(R.id.change);
        showtext = (EditText) findViewById(R.id.text);
        showtext.setCursorVisible(false);

        zero.setOnClickListener(this);//添加按钮事件
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        equal.setOnClickListener(this);
        delete.setOnClickListener(this);
        clear.setOnClickListener(this);
        point.setOnClickListener(this);
        change.setOnClickListener(this);
        showtext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero://记录输入的数字
                Button b = (Button) v;
                OperateSum = AddSum(b.getText().charAt(0));//把数字添加进OperateSum
                showtext.setText(OperateSum);   //把输入的数字显示在EditText
                break;
            case R.id.add:
            //case R.id.subtract:
            case R.id.multiply:
            case R.id.divide://记录符号
                Button b1 = (Button) v;
                OperateSum = AddSum(b1.getText().charAt(0));
                Operator[i] = b1.getText().charAt(0);       //记录符号
                i++;
                showtext.setText(OperateSum);   //把输入的符号显示在EditText
                break;
            case R.id.subtract:
                Button b2 = (Button) v;
                if (CheckOperatesum()) {
                    Operator[i] = b2.getText().charAt(0);       //记录符号
                    i++;
                }
                OperateSum = AddSum(b2.getText().charAt(0));
                showtext.setText(OperateSum);   //把输入的符号显示在EditText
                break;
            case R.id.point:
                OperateSum = AddSum('.');
                showtext.setText(OperateSum);
                break;
            case R.id.delete:       //删除刚刚输入的一个符号
                if (OperateSum.length() >= 1)//当至少已经输入了一个符号才执行
                {
                    OperateSum = OperateSum.substring(0, OperateSum.length() - 1);
                }
                showtext.setText(OperateSum);
                break;
            case R.id.clear:        //清除整个显示框
                OperateSum = "";      //变量全部初始化
                num1 = 0;
                num2 = 0;
                sum = 0;
                for (int m = 0; m < i; m++)
                    Operator[m] = ' ';
                i = 0;
                j = 0;
                flag = 0;
                showtext.setText(OperateSum);
                break;
            case R.id.equal:
                if (CheckInput(OperateSum))//当输入的数字和运算符都正确，才进行计算
                {
                    if (Operator[i - 1] != '#') {
                        try {
                            OperateSum = OperateSum + "=" + String.valueOf(equals(OperateSum));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        showtext.setText(OperateSum);//显示数字运算符和结果
                        OperateSum = String.valueOf(sum); //保存运算结果，以便再直接输入一个运算符和一个数字进行下一次运算
                        for (int m = 0; m < i; m++)
                            Operator[m] = ' ';
                        i=0;
                    } else
                        try {
                            flag = equals(OperateSum);
                            flag = 0;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                } else                        //输入不合理弹出警告
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.change:
                OperateSum = AddSum('#');
                Operator[i] = '#';       //记录符号
                i++;
                showtext.setText(OperateSum);   //把输入的符号显示在EditText
                break;
            default:
                break;

        }
    }

    public boolean CheckOperatesum()//检测该符号代表减号还是负号
    {
        if (OperateSum.isEmpty())
            return false;
        if (OperateSum.charAt(OperateSum.length() - 1) == '+')
            return false;
        if (OperateSum.charAt(OperateSum.length() - 1) == '-')
            return false;
        if (OperateSum.charAt(OperateSum.length() - 1) == '*')
            return false;
        if (OperateSum.charAt(OperateSum.length() - 1) == '÷')
            return false;
        return true;
    }

    public String AddSum(char c)//添加并记录一个输入的数字或符号
    {
        OperateSum = OperateSum + String.valueOf(c);
        return OperateSum;
    }

    public boolean CheckInput(String OperateSum)    //这个方法用来检查用户输入的数字是否合理，比如用户输入了"9/3"这是合理的，加入输入了 " 8/*",这不合理
    {
        if (OperateSum.length() <= 2)//至少要分别输入了一个数字和一个运算符和一个数字，输入长度<=2肯定不合理，如输入 99 按下=按钮肯定不计算
        {
            return false;
        }
//        if (OperateSum.indexOf(Operator, 1) == -1)     //如果没有输入运算符，肯定不合理
//        {
//            return false;
//        }

        if (OperateSum.endsWith(String.valueOf(Operator)))       //最后以运算符结尾而不是数字，肯定不合理 如输入 9** ，不进行计算
        {
            return false;
        }
        return true;
    }

    public double equals(String OperateSum) throws Exception         //计算结果
    {
        int index1 = 0, index2 = 0;
        num1 = 0;
        num2 = 0;
        j = 0;//j为已处理算术符数量
        while (j < i) {
            if (j == 0)
                index1 = OperateSum.indexOf(Operator[j], 1);
                //计算运算符在从输入的OperateSum字符串里的位置
            else
                index1 = index2;

            if (j + 1 != i) {
                index2 = OperateSum.indexOf(Operator[j + 1], index1 + 1);
            } else {
                index2 = OperateSum.length();
            }
            if (OperateSum.length() >= 3) {
                if (j == 0)
                    num1 = Double.parseDouble(OperateSum.substring(0, index1));
                else
                    num1 = sum;
                num2 = Double.parseDouble(OperateSum.substring(index1 + 1, index2));
            }
            switch (Operator[j])       //根据运算符进行计算
            {
                case '+':           //加法运算
                    sum = num1 + num2;
                    break;
                case '-':           //减法运算
                    sum = num1 - num2;
                    break;
                case '*':           //乘法运算
                    sum = num1 * num2;
                    break;
                case '÷':           //除法运算
                    if (num2 != 0)
                        sum = num1 / num2;
                    else                        //输入不合理弹出警告
                    {
                        Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case '#':
                    if (num2 - (int) num2 < 0.0000000001) {
                        change(num1, num2);
                    } else {
                        Toast.makeText(this, "#后应为整数", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
            j++;
        }
        return sum;         //返回结果
    }

    public void change(double value, double nu) throws Exception {//将value转换为nu进制
        int n = (int) nu;
        int in = (int) value;// 整数部分的值
        double r = value - in;// 小数部分的值
        StringBuilder stringBuilder = new StringBuilder();// 将整数部分转化为n进制

        int remainder = 0;
        int quotient = 0;
        if(value<0)
            in=0-in;
        while (in != 0) {
            quotient = in / n;// 得商
            remainder = in % n;// 得余数
            if (remainder < 10)//当进制数小于10时直接添加
                stringBuilder.append(remainder);
            else {
                switch (remainder) {
                    case 10:
                        stringBuilder.append('A');
                        break;
                    case 11:
                        stringBuilder.append('B');
                        break;
                    case 12:
                        stringBuilder.append('C');
                        break;
                    case 13:
                        stringBuilder.append('D');
                        break;
                    case 14:
                        stringBuilder.append('E');
                        break;
                    case 15:
                        stringBuilder.append('F');
                        break;
                }
            }
            in = quotient;
        }
        if(value<0)
            stringBuilder.append('-');
        stringBuilder.reverse();//字符串倒置
        if (r > 0.0000000001) {
            if ((int) value == 0) {//若整数部分为零
                if(value<0)
                    stringBuilder.append('-');
                stringBuilder.append("0");
            }

            stringBuilder.append(".");
            // 将小数部分转化为n进制
            int count = 16; // 限制小数部分位数最多为16位，如果超过16为则抛出异常
            double num = 0;
            while (r > 0.0000000001) {
                count--;
                if (count == 0) {
                    throw new Exception("Cannot change the decimal number to binary!");
                }
                num = r * n;
                if (num >= 1) {
                    stringBuilder.append((int) num);
                    r = num - (int) num;
                } else {
                    stringBuilder.append((int) num);
                    r = num;
                }
            }
        }
        OperateSum = OperateSum + "=" + stringBuilder;
        showtext.setText(OperateSum);//显示数字运算符和结果
        OperateSum = stringBuilder.toString(); //保存运算结果，以便再直接输入一个运算符和一个数字进行下一次运算
        for (int m = 0; m < i; m++)
            Operator[m] = ' ';
        i=0;
    }
}
