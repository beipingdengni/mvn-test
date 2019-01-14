#!/usr/bin/env bash

# 参考博客   https://www.cnblogs.com/yinheyi/p/6648242.html

# java 启动
# nohup java -server -Xss128k -Xmx1024M -Xmx1024M -XX:MaxNewSize=512m  *.jar >/dev/null 2>&1 >&
# java -server -Xms256m -Xmx256m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=2347 -Djava.ext.dirs=. cn.mwee.monitor.paidui.PDMonitor
# nohup sh *.sh &

country="China"

echo $country
echo ${country}
echo "I love my ${country} ==abcd!"

#重定义变量
a="111"
a="2222"
#只读变量: 用 readonly 命令
readonly country="China"
#删除变量: 使用unset命令可以删除变量，但是不能删除只读的变量
unset variable_name

#shell 脚本文件名
echo $0  "脚本文件名"
#当前shell id
echo $$  "shell id"
#shell 传递参数的内容
echo $@ " 参数的内容"
#shell 传递参数的个数
echo $#   "传递参数的个数"

# 加减乘除
a=1011
b=2011
c= expr $a + $b
#echo $c

dir=$(pwd)
echo $dir  "目录输出"

a1=21
b1=2
# 比较数字
if [ $a1 -eq $b1 ]; then
    echo "ok"
fi

# -a 与  | -o 或 | ！非
# 比较字符串   [-z $a] 字符长度为0，true | [-n $a] 字符串不为0，true | [$a] 字符串不为空，true
b1="21"
if [ $a1 = $b1 ]; then
    echo "if =="
    elif [ -n $a ];then
    echo "elif =="
    else
    echo "else =="
fi

# bashname   -a,表示处理多个路径    -s, 用于去掉指定的文件的后缀名；
# basename /home/yin/1.txt    -> 1.txt
# basename -s .txt /home/yin/1.txt    -> 1

# 数组
#array_name=(value0 value1 value2 value3)
# 赋值 array_name[0]=value0
# 读取 ${array_name[index]}
# 读取全部  ${array_name[*]}  或 ${array_name[@]}
# 数组个数  length=${#array_name[*]}  或 length=${#array_name[@]}

# case .... esac
a=2
case $a in
    1)
        echo "==1"
    ;;
    2)
        echo "==2"
    ;;
    3)
        echo "==3"
    ;;
    *)
        echo "==*"
    ;;
esac

# for a in list  do echo $a done
for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done

# while 处理
#COUNTER=0
#while [ $COUNTER -lt 5 ]
#do
#    COUNTER='expr $COUNTER+1'
#    echo $COUNTER
#done

# 像删除变量一样，删除函数也可以使用 unset 命令，不过要加上 .f 选项
# unset .f function_name
funWithParam(){
    echo "The value of the first parameter is $1 !"
    echo "The value of the second parameter is $2 !"
    echo "The value of the tenth parameter is ${10} !"
    echo "The value of the eleventh parameter is ${11} !"
    echo "The amount of the parameters is $# !"  # 参数个数
    echo "The string of the parameters is $* !"  # 传递给函数的所有参数
}
funWithParam 1 2 3 4 5 6 7 8 9 34 73

# Shell 也可以包含外部脚本，将外部脚本的内容合并到当前脚本
# . filename 或  source filename

# 在filename文本最后一行追加hello world
#  sed '$a hello world' filename
# 在filename文本第一行插入hello world
# sed '1i hello world' filename
# 表示将filename文本的每行中的oldstring替换为newstring   sed 's/oldstring/newstring/g' filename
# 删除空白行     sed '/^\s*$/d' filename

#awk命令主要用于文本内容的分析处理。
#以”:”分隔filename文本的每一行并且打印第一列
#   awk -F ':' '{print $1}' filename

#文本test.txt，内容为   This is my cat, my cat's name is betty
#需要显示的结果为   cat:betty
#采用sed  sed 's/This is my \(.*\),.*is \(.*\)/\1:\2/g' test.txt
#采用awk  awk -F '[ ,]' '{print $4,$10}' OFS=":" test.txt  或 awk -F, '{print $1,$2}' test.txt|awk '{print $4,$9}' OFS=":"
















