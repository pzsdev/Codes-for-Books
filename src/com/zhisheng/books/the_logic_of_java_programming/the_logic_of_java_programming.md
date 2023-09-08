# 《Java 编程的逻辑》笔记

> 作者-马昌俊

## 第二部分 面向对象

### 第3章 类的基础

### 第4章 类的继承

### 4.3 继承实现的基本原理
本节通过一个例子来介绍继承实现的基本原理。需要说明的是，本节主要从概念上来介绍原理，实际实现细节可能与此不同。

#### 4.3.1 示例
> @see ./chapter4

下面是解释一下背后都发生了一些什么事情，从类的加载开始。

##### 4.3.2 类加载过程
在 Java 中，所谓类的加载是指将类的相关信息加载到内存。   
在 Java 中，类是动态加载的，当第一次使用这个类的时候才会加载，加载一个类时，会查看其父类是否已加载，如果没有，则会加载其父类。   


### 第 7 章 常用的基础类

#### 7.1 包装类
Java有8种基本类型，每种基本类型都有一个对应的包装类。   
包装类是什么呢？它是一个类，内部有一个实例变量，保存对应的基本类型的值，这个类一般还有一些静态方法、静态变量和实例方法，以方便对数据进行操作。

##### 7.1.1 基本用法
每种包装类都有一个静态方法 valueOf()，接受基本类型，返回引用类型，也都有一个实例方法xxxValue()返回对应的基本类型。

Java 5以后引入了自动装箱和拆箱技术，可以直接将基本类型赋值给引用类型。

自动装箱/拆箱是 Java 编译器提供的能力，背后，它会替换为调用对应的valueOf/xxx-Value方法。

一般建议使用valueOf方法。new每次都会创建一个新对象，而除了Float和Double外的其他包装类，都会缓存包装类对象，减少需要创建对象的次数，节省空间，提升性能。实际上，从Java 9开始，这些构造方法已经被标记为过时了，推荐使用静态的valueOf方法。

6种数值类型包装类有一个共同的父类Number。Number是一个抽象类。

#### 7.1.3 剖析 Integer 与二进制算法

**3. valueOf 的实现** 

```java
public static Integer valueOf(int i) {
    assert IntegerCache.high >= 127;
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

```java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];
    static {
        //high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty(
                        "java.lang.Integer.IntegerCache.high");
        if(integerCacheHighPropValue ! = null) {
            int i = parseInt(integerCacheHighPropValue);
            i = Math.max(i, 127);
            //Maximum array size is Integer.MAX_VALUE
            h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
        }
        high = h;
        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);
    }
    private IntegerCache() {}
}
```

IntegerCache表示Integer缓存，其中的cache变量是一个静态Integer数组，在静态初始化代码块中被初始化，默认情况下，保存了-128～127共256个整数对应的Integer对象。

在valueOf代码中，如果数值位于被缓存的范围，即默认-128～127，则直接从Integer-Cache中获取已预先创建的Integer对象，只有不在缓存范围时，才通过new创建对象。

##### 7.1.4 剖析 Character

Character类除了封装了一个char外，还有很多静态方法，封装了Unicode字符级别的各种操作，是Java文本处理的基础，注意不是char级别，Unicode字符并不等同于char。

Java内部采用UTF-16编码，char表示一个字符，但只能表示BMP中的字符，对于增补字符，需要使用两个char表示，一个表示高代理项，一个表示低代理项。

#### 7.2 剖析 String

##### 7.2.1 基本用法

##### 7.2.2 走进 String 内部

String类内部用一个字符数组表示字符串。

##### 7.2.3 编码转换

String内部是按UTF-16BE处理字符的，对BMP字符，使用一个char，两个字节，对于增补字符，使用两个char，四个字节。

String类提供了如下方法，返回字符串按给定编码的字节表示：
```java
public byte[] getBytes()
public byte[] getBytes(String charsetName)
public byte[] getBytes(Charset charset)
```

##### 7.2.5 不可变性

与包装类类似，String类也是不可变类，即对象一旦创建，就没有办法修改了。

String类也声明为了final，不能被继承，内部char数组value也是final的，初始化后就不能再变了。String类中提供了很多看似修改的方法，其实是通过创建新的String对象来实现的，原来的String对象不会被修改。

#### 7.3 剖析 StringBuilder

如果字符串修改操作比较频繁，应该采用StringBuilder和StringBuffer类，这两个类的方法基本是完全一样的，它们的实现代码也几乎一样，唯一的不同就在于StringBuffer类是线程安全的，而StringBuilder类不是。

##### 7.3.1 基本用法

##### 7.3.2 基本实现原理

与String类似，StringBuilder类也封装了一个字符数组。（Java 7）

与String不同，它不是final的，可以修改。另外，与String不同，字符数组中不一定所有位置都已经被使用，它有一个实例变量，表示数组中已经使用的字符个数。

new StringBuilder()代码内部会创建一个长度为16的字符数组，count的默认值为0。

扩展的逻辑是：分配一个足够长度的新数组，然后将原内容复制到这个新数组中，最后让内部的字符数组指向这个新数组。

这里主要看下newCapacity是怎么算出来的。参数minimumCapacity表示需要的最小长度，需要多少分配多少不就行了吗？不行，因为那就跟String一样了，每append一次，都会进行一次内存分配，效率低下。这里的扩展策略是跟当前长度相关的，当前长度乘以2，再加上2，如果这个长度不够最小需要的长度，才用minimumCapacity。

#### 7.5 剖析日期和时间

##### 5 Calendar

Calendar类是日期和时间操作中的主要类，它表示与TimeZone和Locale相关的日历信息，可以进行各种相关的运算。

Calendar做了一项非常烦琐的工作，根据TimeZone和Locale，在绝对时间毫秒数和日历字段之间自动进行转换，且对不同日历字段的修改进行自动同步更新。

##### DateFormat

##### 6. SimpleDateFormat

SimpleDateFormat是DateFormat的子类，相比DateFormat，它的一个主要不同是，它可以接受一个自定义的模式（pattern）作为参数，这个模式规定了Date的字符串形式。

#### 7.6 随机

