## Java安全相关知识

不详细介绍，具体的知识随着看代码写代码也都逐渐可以掌握

### 访问控制权限

访问控制权限主要包含private（私有）（get set方法），package（包访问权限），protected（子类访问权限），public（公共访问权限）

|            | private | package | protected | public |
| :--------: | :-----: | :-----: | :-------: | :----: |
| 同一个类中 |    √    |    √    |     √     |   √    |
| 同一个包中 |         |    √    |     √     |   √    |
|   子类中   |         |         |     √     |   √    |
| 全局范围内 |         |         |           |   √    |

### String类

1. == and equals

    == 比较引用，比较堆区位置
    
    equals 比较内容

2. 实例化

    直接赋值存放到对象池中，如果存在则指向同一个堆区位置
    
    new则每次创建一个新的字符串对象

3. 字符串的内容不可变性

4. String类

    1. char charAt(int index) 返回索引位置char
    2. int length()
    3. int indexOf(char a) 返回第一次出现的索引
    4. String substring(int beginIndex)
    5. String toUpperCase()


