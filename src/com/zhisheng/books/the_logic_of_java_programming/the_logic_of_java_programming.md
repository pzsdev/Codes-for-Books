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

## 第三部分 泛型与容器

### 第 8 章 泛型

#### 8.1 基本概念和原理
泛型将接口的概念进一步延伸，“泛型”的字面意思就是广泛的类型。

在Java中，类、接口、方法都可以是泛型的。

##### 8.1.1 一个简单泛型类
**1. 基本概念**
```java
public class Pair<T> {
    T first;
    T second;
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() {
        return first;
    }
    
    public T getSecond() {
        return second;
    }
}
```
Pair就是一个泛型类，与普通类的区别体现在：   
1）类名后面多了一个`<T>`；   
2）first 和 second 的类型都是T。

`T`是什么呢？`T`表示`类型参数`，泛型就是`类型参数化`，处理的数据类型不是固定的，而是可以作为参数传入。

**2. 基本原理**   
泛型擦除

Java泛型是通过擦除实现的，类定义中的类型参数如`T`会被替换为`Object`，在程序运行过程中，不知道泛型的实际类型参数，比如Pair<Integer>，运行中只知道Pair，而不知道Integer。   
认识到这一点是非常重要的，它有助于我们理解Java泛型的很多限制。   

Java有Java编译器和Java虚拟机，编译器将Java源代码转换为．class文件，虚拟机加载并运行．class文件。   
对于泛型类，Java编译器会将泛型代码转换为普通的非泛型代码，就像上面的普通Pair类代码及其使用代码一样，将类型参数T擦除，替换为Object，插入必要的强制类型转换。   
Java虚拟机实际执行的时候，它是不知道泛型这回事的，只知道普通的类及代码。

**3. 泛型的好处**   
泛型主要有两个好处：   
- 更好的安全性。
- 更好的可读性。

通过使用泛型，开发环境和编译器能确保不会用错类型，为程序多设置一道安全防护网。   
使用泛型，还可以省去烦琐的强制类型转换，再加上明确的类型信息，代码可读性也会更好。

#### 8.1.2 容器类
泛型类最常见的用途是作为容器类。所谓容器类，简单地说，就是容纳并管理多项数据的类。

#### 8.1.3 泛型方法
除了泛型类，方法也可以是泛型的，而且，一个方法是不是泛型的，与它所在的类是不是泛型没有什么关系。

```java
public static <T> int indexOf(T[] arr, T elm) {
    for (int i = 0; i < arr.lengnth(); i++) {
        if (arr[i].equals(elm)) {
            return i;
        }            
    }
    return -1;
}
```
这个方法就是一个泛型方法，类型参数为T，放在返回值前面。

与泛型类一样，类型参数可以有多个，以逗号分隔。
```java
public static <U, V> Pair<U, V> makePair(U first, V second) {
    Pair<U, V> pair = new Pair<>(first, second);
    return pair;
}
```
#### 8.1.4 泛型接口

#### 8.1.5 类型参数的限定
对于类型参数，Java支持限定这个参数的一个上界，也就是说，参数必须为给定的上界类型或其子类型，这个限定是通过`extends`关键字来表示的。   
这个上界可以是某个具体的类或者某个具体的接口，也可以是其他的类型参数。

**1. 上界为某个具体类**   
限定类型后，就可以使用该类型的方法了。

**2. 上界为某个接口**   

**3. 上界为其他类型参数**   
Java支持一个类型参数以另一个类型参数作为上界。   

```java
public <T extends E> void addAll(DynamicArray<T> c) {
    for(int i = 0; i< c.size(); i++) {
        add(c.get(i));
    }
}
```
`E`是DynamicArray的类型参数，`T`是addAll的类型参数，`T`的上界限定为`E`。

#### 8.1.6 总结
在Java中，泛型是通过类型擦除来实现的，它是Java编译器的概念，Java虚拟机运行时对泛型基本一无所知，理解这一点是很重要的，它有助于我们理解Java泛型的很多局限性。

#### 8.2 解析通配符

##### 8.2.1 更简洁的参数类型限定
在8.1节最后，我们提到一个例子，为了将Integer对象添加到Number容器中，我们的类型参数使用了其他类型参数作为上界，我们提到，这种写法有点烦琐，它可以替换为更为简洁的通配符形式：
```java
public void addAll(DynamicArray<? extends E> c) {
    for (int i = 0; i < c.size(); i++) {
        add(c.get(i));    
    }    
}
```
这个方法没有定义类型参数，c的类型是DynamicArray<? extends E>, ？表示通配符，<? extends E>表示有限定通配符，匹配E或E的某个子类型，具体什么子类型是未知的。   

同样是extends关键字，同样应用于泛型，<T extends E>和<? extends E>到底有什么关系？它们用的地方不一样，我们解释一下：   
1）<T extends E>用于定义类型参数，它声明了一个类型参数T，可放在泛型类定义中类名后面、泛型方法返回值前面。   
2）<? extends E>用于实例化类型参数，它用于实例化泛型变量中的类型参数，只是这个具体类型是未知的，只知道它是E或E的某个子类型。


#### 8.3 细节和局限性

### 第 9 章 列表和队列

#### 9.1 剖析 ArrayList
##### 9.1.1 基本用法
##### 9.1.2 基本原理
基于 Java 8   
内部一个数组 elementData，一个整数 size 记录实际的元素个数，DEFAULT_CAPACITY = 10。   

无餐构造函数如下，初始化一个空的数组，（在调用 add 方法时，进行扩容）
```java
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

// private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
```

add 方法：
```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```

调用 `ensureCapacityInternal` 确保数组容量是足够的，`ensureCapacityInternal` 代码是：
```java
private static int calculateCapacity(Object[] elementData, int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    return minCapacity;
}

private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```

数组扩容：
```java
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    // 右移一位相当于除2，所以 newCapacity 是 oldCapacity 的 1.5 倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    // 如果拓展的 1.5 倍还小于 minCapacity，就拓展为 minCapacity
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    // Java 8 增加
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```


remove 方法：
```java
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);
    
    // 要移动的元素的个数
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work

    return oldValue;
}
```

##### 9.1.3 迭代

**1. 迭代器接口**  

ArrayList 实现了 Iterable 接口。
```java
// 实现关系
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable

public interface List<E> extends Collection<E>

public interface Collection<E> extends Iterable<E>

public interface Iterable<T>

```

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }    
    
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

```java
public interface Iterator<E> {
    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
```

**2. ListIterator**   

除了iterator(), ArrayList还提供了两个返回Iterator接口的方法。

ListIterator扩展了Iterator接口，增加了一些方法，向前遍历、添加元素、修改元素、返回索引位置等。

**3. 迭代的陷阱**

关于迭代器，有一种常见的误用，就是在迭代的中间调用容器的删除方法。   

如何避免异常呢？可以使用迭代器的remove方法。   

**4. 迭代器实现的原理**

**5. 迭代器的好处**

为什么要通过迭代器这种方式访问元素呢？
直接使用size()/get(index)语法不也可以吗？
在一些场景下，确实没有什么差别，两者都可以。
不过，foreach语法更为简洁一些，更重要的是，迭代器语法更为通用，它适用于各种容器类。

此外，迭代器表示的是一种**关注点分离**的思想，**将数据的实际组织方式与数据的迭代遍历相分离**，是一种常见的设计模式。
需要访问容器元素的代码只需要一个Iterator接口的引用，不需要关注数据的实际组织方式，可以使用一致和统一的方式进行访问。

##### 9.1.4 ArrayList 实现的接口

ArrayList还实现了三个主要的接口：Collection、List和Random-Access。

##### 9.1.5 ArrayList 的其他方法

##### 9.1.6 ArrayList 特点分析

作为程序员，就是要理解每种数据结构的特点，根据场合的不同，选择不同的数据结构。

对于ArrayList，它的特点是内部采用动态数组实现，这决定了以下几点。   
- 1）可以随机访问，按照索引位置进行访问效率很高，用算法描述中的术语，效率是O(1)，简单说就是可以一步到位。   
- 2）除非数组已排序，否则按照内容查找元素效率比较低，具体是O(N), N为数组内容长度，也就是说，性能与数组长度成正比。   
- 3）添加元素的效率还可以，重新分配和复制数组的开销被平摊了，具体来说，添加N个元素的效率为O(N)。   
- 4）插入和删除元素的效率比较低，因为需要移动元素，具体为O(N)。



#### 9.2 剖析 LinkedList

#### 9.3 剖析 ArrayDeque

