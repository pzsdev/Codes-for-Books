# 《Java 编程的逻辑》笔记

> 作者-马昌俊

## 第二部分 面向对象

### 第3章 类的基础

### 第4章 类的继承

#### 4.3 继承实现的基本原理
本节通过一个例子来介绍继承实现的基本原理。需要说明的是，本节主要从概念上来介绍原理，实际实现细节可能与此不同。

##### 4.3.1 示例
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
LinkedList 除了实现了List接口外，LinkedList还实现了Deque和Queue接口，可以按照队列、栈和双端队列的方式进行操作。

##### 9.2.1 用法

LinkedList与ArrayList一样，同样实现了List接口，而List接口扩展了Collection接口，Collection又扩展了Iterable接口，所有这些接口的方法都是可以使用的。

LinkedList还实现了队列接口Queue，所谓队列就类似于日常生活中的各种排队，特点就是先进先出，在尾部添加元素，从头部删除元素。
所谓队列就类似于日常生活中的各种排队，特点就是先进先出，在尾部添加元素，从头部删除元素。

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable

public interface Deque<E> extends Queue<E>

public interface Queue<E> extends Collection<E>
```

```java
/**
 * add offer 在尾部添加
 * remove poll 在头部删除
 * 
 * 
 * @param <E>
 */
public interface Queue<E> extends Collection<E> {
    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * @param e
     * @return true
     */
    boolean add(E e);

    /**
     * 
     * @param e
     * @return true if the element was added to this queue, else false
     */
    boolean offer(E e);
    
    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();
    
    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element();
    
    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();
}
```

Java中没有单独的栈接口，栈相关方法包括在了表示双端队列的接口Deque中，主要有三个方法：
```java

public interface Deque<E> extends Queue<E> {
    /**
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    void push(E e);
    
    /**
     * Pops an element from the stack represented by this deque.  In other
     * words, removes and returns the first element of this deque.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque (which is the top
     *         of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    E pop();
    
    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * <p>This method is equivalent to {@link #peekFirst()}.
     *
     * @return the head of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    E peek();
}
```

##### 9.2.2 实现原理

**1. 内部组成** 

它的内部实现是双向链表，每个元素在内存都是单独存放的，元素之间通过链接连在一起。

为了表示链接关系，需要一个节点的概念。节点包括实际的元素，但同时有两个链接，分别指向前一个节点（前驱）和后一个节点（后继）。节点是一个内部类，具体定义为：

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```
Node类表示节点，item指向实际的元素，next指向后一个节点，prev指向前一个节点。

LinkedList内部组成就是如下三个实例变量：
```java
    transient int size = 0;

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;
```

size表示链表长度，默认为0, first指向头节点，last指向尾节点，初始值都为null。
LinkedList的所有public方法内部操作的都是这三个实例变量。

**2. add方法**

```java
    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }
    
    /**
     * Links e as last element.
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
```

**3. 根据索引访问元素get**

```java
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    
    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```

**4. 根据内容查找元素**

**5. 插入元素**

**6. 删除元素**

##### LinkedList 特点分析

用法上，LinkedList是一个List，但也实现了Deque接口，可以作为队列、栈和双端队列使用。  
实现原理上，LinkedList内部是一个双向链表，并维护了长度、头节点和尾节点，这决定了它有如下特点。

1）按需分配空间，不需要预先分配很多空间。   
2）不可以随机访问，按照索引位置访问效率比较低，必须从头或尾顺着链接找，效率为O(N/2)。   
3）不管列表是否已排序，只要是按照内容查找元素，效率都比较低，必须逐个比较，效率为O(N)。   
4）在两端添加、删除元素的效率很高，为O(1)。   
5）在中间插入、删除元素，要先定位，效率比较低，为O(N)，但修改本身的效率很高，效率为O(1)。

#### 9.3 剖析 ArrayDeque

LinkedList实现了队列接口Queue和双端队列接口Deque, Java容器类中还有一个双端队列的实现类ArrayDeque，它是基于数组实现的。

ArrayDeque实现了Deque接口，同LinkedList一样，它的队列长度也是没有限制的， Deque扩展了Queue，有队列的所有方法，还可以看作栈，有栈的基本方法push/pop/peek，还有明确的操作两端的方法如addFirst/removeLast等。

##### 9.3.1 实现原理

ArrayDeque内部主要有如下实例变量：
```java
public class ArrayDeque<E> extends AbstractCollection<E>
                           implements Deque<E>, Cloneable, Serializable
{
    transient Object[] elements;
    transient int head;
    transient int tail;
}
```

elements就是存储元素的数组。ArrayDeque的高效来源于head和tail这两个变量，它们使得物理上简单的从头到尾的数组变为了一个逻辑上循环的数组，避免了在头尾操作时的移动。

**1. 循环数组**

**2. 构造方法**

**3. 从尾部添加**

**4. 从头部添加**

**5. 从头部删除**

**6. 检查长度**

**7. 检查给定元素是否存在**

**8. toArray 方法**

**9. 原理小结**

以上就是ArrayDeque的基本原理，内部它是一个动态扩展的循环数组，通过head和tail变量维护数组的开始和结尾，数组长度为2的幂次方，使用高效的位操作进行各种判断，以及对head和tail进行维护。

##### 9.3.2 ArrayDeque 特点分析

ArrayDeque实现了双端队列，内部使用循环数组实现，这决定了它有如下特点。   
1）在两端添加、删除元素的效率很高，动态扩展需要的内存分配以及数组复制开销可以被平摊，具体来说，添加N个元素的效率为O(N)。   
2）根据元素内容查找和删除的效率比较低，为O(N)。   
3）与ArrayList和LinkedList不同，没有索引位置的概念，不能根据索引位置进行操作。   

ArrayDeque和LinkedList都实现了Deque接口，应该用哪一个呢？   
如果只需要Deque接口，从两端进行操作，一般而言，ArrayDeque效率更高一些，应该被优先使用；如果同时需要根据索引位置进行操作，或者经常需要在中间进行插入和删除，则应该选LinkedList。

无论是ArrayList、LinkedList还是Array-Deque，按内容查找元素的效率都很低，都需要逐个进行比较。

### 第 10 章 Map 和 Set

#### 10.1 剖析 HashMap

##### 10.1.1 Map 接口

##### 10.1.2 HashMap

##### 10.1.3 实现原理

**1. 内部组成**

```java
// Java 8
/**
 * The table, initialized on first use, and resized as
 * necessary. When allocated, length is always a power of two.
 * (We also tolerate length zero in some operations to allow
 * bootstrapping mechanics that are currently not needed.)
 */
transient Node<K,V>[] table; // Node 类型数组，成为哈希表或哈希桶，每个元素指向一个单链表，链表中每个节点表示一个键值对

/**
 * The number of key-value mappings contained in this map.
 */
transient int size; // 实际键值对个数

/**
 * The next size value at which to resize (capacity * load factor).
 *
 * @serial
 */
        // (The javadoc description is true upon serialization.
        // Additionally, if the table array has not been allocated, this
        // field holds the initial array capacity, or zero signifying
        // DEFAULT_INITIAL_CAPACITY.)
int threshold; // 阈值，当 size 大于等于 threashold 时考虑进行拓展

/**
 * The load factor for the hash table.
 *
 * @serial
 */
final float loadFactor; // 负载因子，默认 0.75
```
**2. 默认构造方法**

```java
// 主要设置 loadFactor 的值
/**
 * Constructs an empty <tt>HashMap</tt> with the default initial capacity
 * (16) and the default load factor (0.75).
 */
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}

/**
 * The load factor used when none specified in constructor.
 */
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

**3. 保存键值对**

```java
// hashmap.put 方法（Java 8）
/**
 * Associates the specified value with the specified key in this map.
 * If the map previously contained a mapping for the key, the old
 * value is replaced.
 *
 * @param key key with which the specified value is to be associated
 * @param value value to be associated with the specified key
 * @return the previous value associated with <tt>key</tt>, or
 *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
 *         (A <tt>null</tt> return can also indicate that the map
 *         previously associated <tt>null</tt> with <tt>key</tt>.)
 */
public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
}

/**
 * 计算 key 的 hash 值
 * 
 * Computes key.hashCode() and spreads (XORs) higher bits of hash
 * to lower.  Because the table uses power-of-two masking, sets of
 * hashes that vary only in bits above the current mask will
 * always collide. (Among known examples are sets of Float keys
 * holding consecutive whole numbers in small tables.)  So we
 * apply a transform that spreads the impact of higher bits
 * downward. There is a tradeoff between speed, utility, and
 * quality of bit-spreading. Because many common sets of hashes
 * are already reasonably distributed (so don't benefit from
 * spreading), and because we use trees to handle large sets of
 * collisions in bins, we just XOR some shifted bits in the
 * cheapest possible way to reduce systematic lossage, as well as
 * to incorporate impact of the highest bits that would otherwise
 * never be used in index calculations because of table bounds.
 */
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

/**
 * Implements Map.put and related methods.
 *
 * @param hash hash for key
 * @param key the key
 * @param value the value to put
 * @param onlyIfAbsent if true, don't change existing value
 * @param evict if false, the table is in creation mode.
 * @return previous value, or null if none
 */
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
        boolean evict) {
        Node<K,V>[] tab; // 当前 hash 桶
        Node<K,V> p; // hash 桶中的一个（将要放置元素的）槽位
        int n, i;
        
        // 当前 hash 桶为空的话，初始化 hash 桶，即初始化 HashMap
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length; // 扩容
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null); // 没有 hash 冲突
        else { // 有 hash 冲突时，构建链表 或者 升级成树
            Node<K,V> e;
            K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p; // key 的 hash 相同，覆盖原来的节点
            else if (p instanceof TreeNode) // 树结构，插入树中
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else { // 插入链表中
                for (int binCount = 0; ; ++binCount) {
                    // next 为空，就到了链表的尾部了
                    if ((e = p.next) == null) {
                        // 将当前 key value 做成 node 链接到链表的尾部
                        p.next = newNode(hash, key, value, null);
                        // TREEIFY_THRESHOLD = 8， 循环了 8 次，就把链表转成树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold) // 大于临界值，进行扩容
            resize();
        afterNodeInsertion(evict);
        return null;
}


/**
 * Initializes or doubles table size.  If null, allocates in
 * accord with initial capacity target held in field threshold.
 * Otherwise, because we are using power-of-two expansion, the
 * elements from each bin must either stay at same index, or move
 * with a power of two offset in the new table.
 *
 * @return the table
 */
final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table; // 旧的 hash 桶，初始化时为 null
        int oldCap = (oldTab == null) ? 0 : oldTab.length; // 旧桶容量，初始化为 0
        int oldThr = threshold; // 旧的（可以存放元素桶的）阈值，初始化时为 0
        int newCap, newThr = 0; // 新的容量，新的阈值
        
        if (oldCap > 0) { // 1、旧 hash 桶不为空
            // static final int MAXIMUM_CAPACITY = 1 << 30;
            // 旧桶是否大于或等于最大容量
            if (oldCap >= MAXIMUM_CAPACITY) { 
                threshold = Integer.MAX_VALUE; // 更新阈值
                return oldTab;
            }
            // 新桶容量等于旧的两倍，同时小于最大容量，同时大于等于默认容量16
            // static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
            // newThr 新的阈值也翻倍，12 * 2 = 24
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
            oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        // 2、oldCap == 0 && oldThr > 0，
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            // 3、oldCap == 0 && oldThr == 0，两个都为 0，就是 hashMap 初始化的时候
            newCap = DEFAULT_INITIAL_CAPACITY; // 16
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY); // 16 * 0.75 = 12
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                (int)ft : Integer.MAX_VALUE);
        }
        // 刷新阈值
        threshold = newThr;
        // 扩容为一个新的 hash 桶
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        
        if (oldTab != null) {
            // 遍历元素
            for (int j = 0; j < oldCap; ++j) {
                // 获取每个桶
                Node<K,V> e; // 桶元素
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) // 桶中没有元素时
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode) // 桶中元素为树节点时，树节点的 rehashing
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order， 链表的 rehashing
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
        }
```

**4. 查找方法**

HashMap支持key为null，key为null的时候，放在table[0]，调用getForNullKey（）获取值。

**5. 根据健删除键值对**

**6. 实现原理小结**

HashMap的用法和实现原理，它实现了Map接口，可以方便地按照键存取值，内部使用数组链表和哈希的方式进行实现。

这决定了它有如下特点：   
1）根据键保存和获取值的效率都很高，为O(1)，每个单向链表往往只有一个或少数几个节点，根据hash值就可以直接快速定位；   
2）HashMap中的键值对没有顺序，因为hash值是随机的。  

如果经常需要根据键存取值，而且不要求顺序，那么HashMap就是理想的选择。

#### 10.2 剖析 HashSet

##### 10.2.1 用法

Set表示的是没有重复元素、且不保证顺序的容器接口，它扩展了Collection，但没有定义任何新的方法，不过，对于其中的一些方法，它有自己的规范。

##### 10.2.2 实现原理

HashSet内部是用HashMap实现的，它内部有一个HashMap实例变量。
```java
private transient HashMap<E,Object> map;
```

我们知道，Map有键和值，HashSet相当于只有键，值都是相同的固定值，这个值的定义为:
```java
// Dummy value to associate with an Object in the backing Map
private static final Object PRESENT = new Object();
```

HashMap中一个键只会保存一份，所以重复添加HashMap不会变化。

##### 10.2.3 小结

本节介绍了HashSet的用法和实现原理，它实现了Set接口，内部实现利用了HashMap，有如下特点：   
1）没有重复元素；   
2）可以高效地添加、删除元素、判断元素是否存在，效率都为O(1)；   
3）没有顺序。

HashSet可以方便高效地实现去重、集合运算等功能。

#### 10.3 排序二叉树

##### 10.3.1 基本概念

HashMap和HashSet的共同实现机制是哈希表，一个共同的限制是没有顺序，我们提到，它们都有一个能保持顺序的对应类TreeMap和TreeSet，这两个类的共同实现基础是排序二叉树。

先来说树的概念。现实中，树是从下往上长的，树会分叉，在计算机程序中，一般而言，与现实相反，树是从上往下长的，也会分叉，有个根节点，每个节点可以有一个或多个孩子节点，没有孩子节点的节点一般称为叶子节点。   

二叉树是一棵树，每个节点最多有两个孩子节点，一左一右，左边的称为左孩子，右边的称为右孩子。

树有一个高度或深度的概念，是从根到叶子节点经过的节点个数的最大值。

排序二叉树也是二叉树，但它没有重复元素，而且是有序的二叉树。什么顺序呢？对每个节点而言：   
❑ 如果左子树不为空，则左子树上的所有节点都小于该节点；   
❑ 如果右子树不为空，则右子树上的所有节点都大于该节点。

##### 10.3.2 基本算法

**1. 查找**
排序二叉树有一个很好的优点，在其中查找一个元素时很方便、也很高效。

基本步骤为：   
1）首先与根节点比较，如果相同，就找到了；   
2）如果小于根节点，则到左子树中递归查找；   
3）如果大于根节点，则到右子树中递归查找。

**2. 遍历**
- 递归的方式
- 按序遍历

**3. 插入**

**4. 删除**

从排序二叉树中删除一个节点要复杂一些，有三种情况：   
❑ 节点为叶子节点；   
❑ 节点只有一个孩子节点；   
❑ 节点有两个孩子节点。

##### 10.3.3 平衡的排序二叉树

排序二叉树的形状与插入和删除的顺序密切相关，极端情况下，排序二叉树可能退化为一个链表。

退化为链表后，排序二叉树的优点就都没有了，即使没有退化为链表，如果排序二叉树高度不平衡，效率也会变得很低。

平衡具体定义是什么呢？有一种高度平衡的定义，即任何节点的左右子树的高度差最多为一。满足这个平衡定义的排序二叉树又被称为AVL树。

在TreeMap的实现中，用的并不是AVL树，而是红黑树，与AVL树类似，红黑树也是一种平衡的排序二叉树，
也是在插入和删除节点时通过旋转操作来平衡的，但它并不是高度平衡的，而是大致平衡的。
所谓大致是指，它确保任意一条从根到叶子节点的路径，没有任何一条路径的长度会比其他路径长过两倍。
红黑树减弱了对平衡的要求，但降低了保持平衡需要的开销，在实际应用中，统计性能高于AVL树。

##### 10.3.4 小结

与哈希表一样，树也是计算机程序中一种重要的数据结构和思维方式。   

为了能够快速操作数据，哈希和树是两种基本的思维方式，不需要顺序，优先考虑哈希，需要顺序，考虑树。   

除了容器类TreeMap/TreeSet，数据库中的索引结构也是基于树的（不过基于B树，而不是二叉树），而索引是能够在大量数据中快速访问数据的关键。

#### 10.4 剖析 TreeMap

##### 10.4.1 基本用法

```java
// 两个基本的构造方法
public class TreeMap<K,V>
    extends AbstractMap<K,V>
    implements NavigableMap<K,V>, Cloneable, java.io.Serializable
{
    /**
     * Constructs a new, empty tree map, using the natural ordering of its
     * keys.  All keys inserted into the map must implement the {@link
     * Comparable} interface.  Furthermore, all such keys must be
     * <em>mutually comparable</em>: {@code k1.compareTo(k2)} must not throw
     * a {@code ClassCastException} for any keys {@code k1} and
     * {@code k2} in the map.  If the user attempts to put a key into the
     * map that violates this constraint (for example, the user attempts to
     * put a string key into a map whose keys are integers), the
     * {@code put(Object key, Object value)} call will throw a
     * {@code ClassCastException}.
     */
    public TreeMap() {
        comparator = null;
    }

    /**
     * Constructs a new, empty tree map, ordered according to the given
     * comparator.  All keys inserted into the map must be <em>mutually
     * comparable</em> by the given comparator: {@code comparator.compare(k1,
     * k2)} must not throw a {@code ClassCastException} for any keys
     * {@code k1} and {@code k2} in the map.  If the user attempts to put
     * a key into the map that violates this constraint, the {@code put(Object
     * key, Object value)} call will throw a
     * {@code ClassCastException}.
     *
     * @param comparator the comparator that will be used to order this map.
     *        If {@code null}, the {@linkplain Comparable natural
     *        ordering} of the keys will be used.
     */
    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }
}
```

TreeMap是按键而不是按值有序，无论哪一种，都是对键而非值进行比较。

##### 10.4.2 实现原理

TreeMap内部是用红黑树实现的，红黑树是一种大致平衡的排序二叉树。

**1. 内部组成**

**2. 保存键值对**

**3. 根据键获取值**

**4. 查看是否包含某个值**

**5. 根据键检出键值对**

##### 10.4.3 小结

本节介绍了TreeMap的用法和实现原理，与HashMap相比，TreeMap同样实现了Map接口，
但内部使用红黑树实现。

红黑树是统计效率比较高的大致平衡的排序二叉树，这决定了它有如下特点：   
1）按键有序，TreeMap同样实现了SortedMap和NavigableMap接口，
可以方便地根据键的顺序进行查找，如第一个、最后一个、某一范围的键、邻近键等。   
2）为了按键有序，TreeMap要求键实现Comparable接口或通过构造方法提供一个Com-parator对象。   
3）根据键保存、查找、删除的效率比较高，为O(h), h为树的高度，在树平衡的情况下，h为log2(N),N为节点数。

#### 10.5 剖析 TreeSet

##### 10.5.3 小结

本节介绍了TreeSet的用法和实现原理，在用法方面，它实现了Set接口，
但有序，在内部实现上，它基于TreeMap实现，而TreeMap基于大致平衡的排序二叉树：
红黑树，这决定了它有如下特点。   
1）没有重复元素。   
2）添加、删除元素、判断元素是否存在，效率比较高，为O(log2(N)), N为元素个数。   
3）有序，TreeSet同样实现了SortedSet和NavigatableSet接口，
可以方便地根据顺序进行查找和操作，如第一个、最后一个、某一取值范围、
某一值的邻近元素等。   
4）为了有序，TreeSet要求元素实现Comparable接口或通过构造方法提供一个Com-parator对象。

#### 10.6 剖析 LinkedHashMap

##### 10.6.1 基本用法

LinkedHashMap是HashMap的子类，但内部还有一个双向链表维护键值对的顺序，每个键值对既位于哈希表中，也位于这个双向链表中。
LinkedHashMap支持两种顺序：一种是插入顺序；另外一种是访问顺序。

插入顺序容易理解，先添加的在前面，后添加的在后面，修改操作不影响顺序。
访问顺序是什么意思呢？所谓访问是指get/put操作，对一个键执行get/put操作后，
其对应的键值对会移到链表末尾，所以，最末尾的是最近访问的，最开始的最久没被访问的，
这种顺序就是访问顺序。

LinkedHashMap有5个构造方法，其中4个都是按插入顺序，只有一个构造方法可以指定按访问顺序，如下所示：
```java
/**
 * Constructs an empty <tt>LinkedHashMap</tt> instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * @param  initialCapacity the initial capacity
 * @param  loadFactor      the load factor
 * @param  accessOrder     the ordering mode - <tt>true</tt> for
 *         access-order, <tt>false</tt> for insertion-order
 * @throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
public LinkedHashMap(int initialCapacity,
                     float loadFactor,
                     boolean accessOrder) {
    super(initialCapacity, loadFactor);
    this.accessOrder = accessOrder;
}
```

其中参数accessOrder就是用来指定是否按访问顺序，如果为true，就是访问顺序。

什么时候希望按访问有序呢？一种典型的应用是LRU缓存。具体看代码示例`LRUCache`。

##### 10.6.2 实现原理

先来看内部组成，再看一些主要方法的实现。LinkedHashMap是HashMap的子类，内部增加了如下实例变量：
```java
/**
 * The head (eldest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> head;

/**
 * The tail (youngest) of the doubly linked list.
 */
transient LinkedHashMap.Entry<K,V> tail;

/**
 * The iteration ordering method for this linked hash map: <tt>true</tt>
 * for access-order, <tt>false</tt> for insertion-order.
 *
 * @serial
 */
final boolean accessOrder;
```

accessOrder表示是按访问顺序还是插入顺序。   
head表示双向链表的头，它的类型Entry是一个内部类，这个类是HashMap.Entry的子类，增加了两个变量before和after，指向链表中的前驱和后继，Entry的完整定义如下所示：
```java
/**
 * HashMap.Node subclass for normal LinkedHashMap entries.
 */
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```
tail表示双向链表的尾节点，它的类型也是 Entry。

LinkedHashMap对HashMap的部分API进行了重写，以此来保证map中元素遍历时的顺序。通过第一部分的一些介绍，其实我们大概才出LinkedHashMap是如何保证顺序的：

1.对于遍历顺序与插入顺序相同的情况，只需要在将元素put到双链表后，维护节点的指针，链入上一次put的节点后面（成为尾结点）；

2.对于遍历顺序与访问顺序相同一致的情况，只需要在get、put、replace..操作之后，将节点移动到末尾即可。

**LinkedHashMap的put**

LinkedHashMap并没有覆盖HashMap的put方法，而是直接沿用HashMap的put方法。

那么LinkedHashMap是如何保证顺序的呢？

是这样的，HashMap在put的时候：

1.如果是新增节点，那么就会创建一个节点，然后放入到map中；

2.如果put操作时修改操作（也就是put的key已经存在），那么不需要创建节点，只需要修改已有节点的value即可；

对于第一种情况，创建节点，是HashMap和LinkedHashMap都有的，LinkedHashMap重写了HashMap创建新节点的方法（newNode和newTreeNode两个方法），在这个时候将新创建的节点加入链表的末尾。

**some post-actions**

由于LinkedHashMap是继承自HashMap，并且大部分的代码都没有做更改，也就是直接沿用HashMap的接口。那么LinkedHashMap是如何保证顺序的呢？特别是前面说的保证插入的顺序，保证访问的顺序？？

其实HashMap的一些API中，比如put方法，其中就包含了一些callback，当插入元素或者查找到元素后，就调用某个方法；这些callback方法在HashMap中没有进行任何操作（方法体为空），留给子类LinkedHashMap进行实现的，如下面所示：

```java
// Callbacks to allow LinkedHashMap post-actions
void afterNodeAccess(Node<K,V> p) { }
void afterNodeInsertion(boolean evict) { }
void afterNodeRemoval(Node<K,V> p) { }
```

##### 10.6.3 LinkedHashSet

LinkedHashSet是HashSet的子类，它内部的Map的实现类是LinkedHashMap，所以它也可以保持插入顺序。

##### 10.6.4 小结

本节主要介绍了LinkedHashMap的用法和实现原理，
用法上，它可以保持插入顺序或访问顺序。插入顺序经常用于处理键值对的数据，
并保持其输入顺序，也经常用于键已经排好序的场景，相比TreeMap效率更高；
访问顺序经常用于实现LRU缓存。

实现原理上，它是HashMap的子类，但内部有一个双向链表以维护节点的顺序。
最后，我们简单介绍了LinkedHashSet，它是HashSet的子类，
但内部使用LinkedHashMap。

#### 10.7 剖析 EnumMap

##### 10.7.1 基本用法

##### 10.7.2 实现原理

##### 10.7.3 小结

#### 10.8 剖析 EnumSet

EnumSet 的实现与EnumMap没有任何关系，而是用极为精简和高效的位向量实现的。   
位向量是计算机程序中解决问题的一种常用方式，我们有必要理解和掌握。

EnumSet可以说是处理枚举类型数据的一把利器，在一些应用领域，它非常方便和高效。

##### 10.8.1 基本用法

与TreeSet/HashSet不同，EnumSet是一个抽象类，不能直接通过new新建。

EnumSet提供了若干静态工厂方法，可以创建EnumSet类型的对象。

##### 10.8.2 应用场景

##### 10.8.3 实现原理

EnumSet是使用位向量实现的，什么是位向量呢？   
就是用一个位表示一个元素的状态，用一组位表示一个集合的状态，每个位对应一个元素，而状态只可能有两种。

EnumSet是一个抽象类，它没有定义使用的向量长度，它有两个子类：RegularEnumSet和JumboEnumSet。   
RegularEnumSet使用一个long类型的变量作为位向量，long类型的位长度是64，而JumboEnumSet使用一个long类型的数组。
如果枚举值个数小于等于64，则静态工厂方法中创建的就是RegularEnumSet，如果大于64就是JumboEnumSet。

##### 10.8.4 小结

本节介绍了EnumSet的用法和实现原理，用法上，它是处理枚举类型数据的一把利器，
简洁方便，实现原理上，它使用位向量，精简高效。

对于只有两种状态，且需要进行集合运算的数据，使用位向量进行表示、
位运算进行处理，是计算机程序中一种常用的思维方式。

Java中有一个更为通用的可动态扩展长度的位向量容器类BitSet，
可以方便地对指定位置的位进行操作，与其他位向量进行位运算，
具体可参看API文档，我们就不介绍了。

### 第 11 章 堆与优先级队列

堆作为一种数据结构可以非常高效方便地嗅觉很多问题，比如：

1）优先级队列，我们之前介绍的队列实现类LinkedList是按添加顺序排列的，
但现实中，经常需要按优先级来，每次都应该处理当前队列中优先级最高的，
高优先级的即使来得晚，也应该被优先处理。   

2）求前K个最大的元素，元素个数不确定，数据量可能很大，甚至源源不断到来，
但需要知道到目前为止的最大的前K个元素。这个问题的变体有：求前K个最小的元素，
求第K个最大的元素，求第K个最小的元素。   

3）求中值元素，中值不是平均值，而是排序后中间那个元素的值，同样，
数据量可能很大，甚至源源不断到来。堆还可以实现排序，称之为堆排序，
不过有比它更好的排序算法，所以，我们就不介绍其在排序中的应用了。

Java容器中有一个类PriorityQueue，表示优先级队列，它实现了堆。

#### 11.1 堆的概念与算法

##### 11.1.1 基本概念

**完全二叉树** 定义：若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。

**满二叉树**：一颗二叉树，除了最后一层外，每个节点都有有两个孩子，而最后一层都是叶子节点，都没有孩子。   
也就是说：如果一棵二叉树的结点要么是叶子结点，要么它有两个子结点，这样的树就是满二叉树。   
也就是说：如果一棵二叉树只有度为0的节点和度为2的节点，并且度为0的节点在同一层上，则这棵二叉树为满二叉树。   

满二叉树国内定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的深度为K，且结点总数是(2^k) -1 ，则它就是满二叉树。

满二叉树一定是完全二叉树。

完全二叉树不要求最后一层是满的，但如果不满，则要求所有节点必须集中在最左边，从左到右是连续的，中间不能有空的。

**节点的度**：一个节点拥有子树的数目称为节点的度。

**叶子节点**：也称为终端节点，没有子树的节点或者度为零的节点。   

**分支节点**：也称为非终端节点，度不为零的节点称为非终端节点。   

完全二叉树有一个重要的特点：给定任意一个节点，可以根据其编号直接快速计算出其父节点和孩子节点编号。

这个特点为什么重要呢？它使得逻辑概念上的二叉树可以方便地存储到数组中，
数组中的元素索引就对应节点的编号，树中的父子关系通过其索引关系隐含维持，不需要单独保持。

使用数组存储的优点是节省空间，而且访问效率高。`堆`逻辑概念上是一棵完全二叉树，
而物理存储上使用数组，还有一定的顺序要求。

与排序二叉树不同，在堆中，可以有重复元素，元素间不是完全有序的，但对于父子节点之间，
有一定的顺序要求。

根据顺序分为两种堆：一种是最大堆，另一种是最小堆。

最大堆是指每个节点都不大于其父节点。这样，对每个父节点，一定不小于其所有孩子节点，
而根节点就是所有节点中最大的，对每个子树，子树的根也是子树所有节点中最大的。
最小堆与最大堆正好相反，每个节点都不小于其父节点。

逻辑概念上，堆是完全二叉树，父子节点间有特定顺序，分为最大堆和最小堆，最大堆根是最大的，
最小堆根是最小的，堆使用数组进行物理存储。

##### 11.1.2 堆的算法

下面，我们介绍如何在堆上进行数据的基本操作。最大堆和最小堆的算法是类似的，我们以**最小堆**来说明。

**1.添加元素**

如果堆为空，则直接添加一个根就行了。我们假定已经有一个堆，要在其中添加元素，基本步骤为：   
1）添加元素到最后位置。   
2）与父节点比较，如果大于等于父节点则满足堆的性质，结束，否则与父节点进行交换，
然后再与父节点比较和交换，直到父节点为空或者大于等于父节点。

添加一个元素，需要比较和交换的次数最多为树的高度，即log2(N), N为节点数。
这种自底向上比较、交换，使得树重新满足堆的性质的过程，我们称为`向上调整（siftup）`。

**2. 从头部删除元素**

在队列中，一般是从头部删除元素，Java中用堆实现优先级队列。

下面介绍如何在堆中删除头部，其基本步骤为：   
1）用最后一个元素替换头部元素，并删掉最后一个元素；   
2）将新的头部与两个孩子节点中较小的比较，如果不大于该孩子节点，则满足堆的性质，结束，
否则与较小的孩子节点进行交换，交换后，再与较小的孩子节点比较和交换，一直到没有孩子节点，
或者不大于两个孩子节点。这个过程称为`向下调整（siftdown）`。

**3. 从中间删除元素**

那如果需要从中间删除某个节点呢？与从头部删除一样，都是先用最后一个元素替换待删元素。
不过替换后，有两种情况：如果该元素大于某孩子节点，则需向下调整（sift-down）；如果小于父节点，则需向上调整（siftup）。

**4. 堆的初始化**

**5. 查找和遍历**

**6. 算法小结**

以上就是堆操作的主要算法，小结如下。   
1）在添加和删除元素时，有两个关键的过程以保持堆的性质，一个是向上调整（siftup），
另一个是向下调整（siftdown），它们的效率都为O(log2(N))。
由无序数组构建堆的过程heapify是一个自底向上循环的过程，效率为O(N)。   
2）查找和遍历就是对数组的查找和遍历，效率为O(N)。

##### 11.1.3 小结

本节介绍了堆这一数据结构的基本概念和算法。
堆是一种比较神奇的数据结构，概念上是树，存储为数组，父子有特殊顺序，
根是最大值/最小值，构建/添加/删除效率都很高，可以高效解决很多问题。

#### 11.2 剖析 PriorityQueue

本节探讨堆在Java中的具体实现类：PriorityQueue。   
顾名思义，PriorityQueue是优先级队列，它首先实现了队列接口（Queue），与LinkedList类似，
它的队列长度也没有限制，与一般队列的区别是，它有优先级的概念，每个元素都有优先级，
队头的元素永远都是优先级最高的。

PriorityQueue内部是用堆实现的，内部元素不是完全有序的，不过，逐个出队会得到有序的输出。

##### 11.2.1 基本用法

查看代码demo。

##### 11.2.2 实现原理

内部组成

构造方法

添加元素

查看头部元素

删除头部元素（出队）

##### 11.2.3 小结

本节介绍了Java中堆的实现类PriorityQueue，它实现了队列接口Queue，但按优先级出队，
内部是用堆实现的，有如下特点：   
1）实现了优先级队列，最先出队的总是优先级最高的，即排序中的第一个。   
2）优先级可以有相同的，内部元素不是完全有序的，如果遍历输出，除了第一个，其他没有特定顺序。   
3）查看头部元素的效率很高，为O(1)，入队、出队效率比较高，为O(log2(N))，
构建堆heapify的效率为O(N)。   
4）根据值查找和删除元素的效率比较低，为O(N)。

#### 11.3 堆和 PriorityQueue 的应用

1）求前K个最大的元素，元素个数不确定，数据量可能很大，甚至源源不断到来，
但需要知道到目前为止的最大的前K个元素。
这个问题的变体有：求前K个最小的元素，求第K个最大的元素，求第K个最小的元素。   

2）求中值元素，中值不是平均值，而是排序后中间那个元素的值，同样，数据量可能很大，
甚至源源不断到来。

##### 11.3.1 求前 K 个最大的元素

解决方法是使用最小堆维护这K个元素，最小堆中，根即第一个元素永远都是最小的，
新来的元素与根比就可以了，如果小于根，则堆不需要变化，否则用新元素替换根，
然后向下调整堆即可，调整的效率为O(log2(K))，这样，总体的效率就是O(N×log2(K))，
这个效率非常高，而且存储成本也很低。使用最小堆之后，第K个最大的元素也很容易获得，
它就是堆的根。

##### 11.3.2 求中值

中值就是排序后中间那个元素的值，如果元素个数为奇数，中值是没有歧义的，但如果是偶数，
中值可能有不同的定义，可以为偏小的那个，也可以是偏大的那个，或者两者的平均值，
或者任意一个，这里，我们假定任意一个都可以。

一个简单的思路是排序，排序后取中间那个值就可以了，排序可以使用Arrays.sort()方法，
效率为O(N× log2(N))。

不过，这要求所有元素都是已知的，而不是动态添加的。如果元素源源不断到来，
如何实时得到当前已经输入的元素序列的中位数？

可以使用两个堆，一个最大堆，一个最小堆，思路如下。

1）假设当前的中位数为m，最大堆维护的是<=m的元素，最小堆维护的是>=m的元素，但两个堆都不包含m。   
2）当新的元素到达时，比如为e，将e与m进行比较，若e<=m，则将其加入最大堆中，
否则将其加入最小堆中。   
3）第2步后，如果此时最小堆和最大堆的元素个数的差值>=2 ，则将m加入元素个数少的堆中，
然后从元素个数多的堆将根节点移除并赋值给m。

代码实现 ChapterEleven.Median。

## 第四部分 文件

### 第 13 章 文件基本技术

#### 13.1 文件概述

##### 13.1.1 基本概念和常识

**1. 二进制思维**

所有文件，不论是可执行文件、图片文件、视频文件、Word文件、压缩文件、txt文件，
都没什么可神秘的，它们都是以0和1的二进制形式保存的。
我们所看到的图片、视频、文本，都是应用程序对这些二进制的解析结果。

**2. 文件类型**

虽然所有数据都是以二进制形式保存的，但为了方便处理数据，高级语言引入了数据类型的概念。
文件处理也类似，所有文件都是以二进制形式保存的，但为了便于理解和处理文件，文件也有文件类型的概念。

文件类型通常以扩展名的形式体现，每种文件类型都有一定的格式，代表着文件含义和二进制之间的映射关系。

有的文件类型的格式是公开的，有的可能是私有的，我们也可以定义自己私有的文件格式。

文件类型可以粗略分为两类：一类是文本文件；另一类是二进制文件。
文本文件的例子有普通的文本文件（.txt），程序源代码文件（.java）、HTML文件（.html）等；
二进制文件的例子有压缩文件（.zip）、PDF文件（.pdf）、MP3文件（.mp3）、Excel文件（.xlsx）等。

**3. 文本文件的编码**

对于文本文件，我们还必须注意文件的编码方式。文本文件中包含的基本都是可打印字符，但字符到二进制的映射（即编码）却有多种方式。

我们需要说明下文本文件的换行符。在Windows系统中，换行符一般是两个字符"\r\n"，
即ASCII码的13（'\r'）和10（'\n'），在Linux系统中，换行符一般是一个字符"\n"。

**4. 文件系统**

在Java中，通过System.getProperty("user.dir")可以得到运行Java程序的当前目录。

操作系统中有一个临时文件的概念。临时文件位于一个特定目录，比如Windows 7中，临时文件一般位于“C:\Users\用户名\AppData\Local\Temp”; Linux系统中，临时文件位于/tmp。

**5. 文件读写**

文件是放在硬盘上的，程序处理文件需要将文件读入内存，修改后，需要写回硬盘。
操作系统提供了对文件读写的基本API，不同操作系统的接口和实现是不一样的，
不过，有一些共同的概念。Java封装了操作系统的功能，提供了统一的API。

一个基本常识是：硬盘的访问延时，相比内存，是很慢的。
操作系统和硬盘一般是按块批量传输，而不是按字节，以摊销延时开销，块大小一般至少为512字节，
即使应用程序只需要文件的一个字节，操作系统也会至少将一个块读进来。
一般而言，应尽量减少接触硬盘，接触一次，就一次多做一些事情。对于网络请求和其他输入输出设备，
原则都是类似的。

另一个基本常识是：一般读写文件需要两次数据复制，
比如读文件，需要先从硬盘复制到操作系统内核，再从内核复制到应用程序分配的内存中。
操作系统运行所在的环境和应用程序是不一样的，操作系统所在的环境是内核态，应用程序是用户态，
应用程序调用操作系统的功能，需要两次环境的切换，先从用户态切到内核态，再从内核态切到用户态。
这种用户态/内核态的切换是有开销的，应尽量减少这种切换。

为了提升文件操作的效率，应用程序经常使用一种常见的策略，即使用缓冲区。
读文件时，即使目前只需要少量内容，但预知还会接着读取，就一次读取比较多的内容，
放到读缓冲区，下次读取时，如果缓冲区有，就直接从缓冲区读，减少访问操作系统和硬盘。
写文件时，先写到写缓冲区，写缓冲区满了之后，再一次性调用操作系统写到硬盘。
不过，需要注意的是，在写结束的时候，要记住将缓冲区的剩余内容同步到硬盘。
操作系统自身也会使用缓冲区，不过，应用程序更了解读写模式，恰当使用往往可以有更高的效率。

操作系统操作文件一般有打开和关闭的概念。打开文件会在操作系统内核建立一个有关该文件的内存结构，
这个结构一般通过一个整数索引来引用，这个索引一般称为文件描述符。
这个结构是消耗内存的，操作系统能同时打开的文件一般也是有限的，在不用文件的时候，
应该记住关闭文件。关闭文件一般会同步缓冲区内容到硬盘，并释放占据的内存结构。

##### 13.1.2 Java 文件概述

在Java中处理文件有一些基本概念和类，包括流、装饰器设计模式、Reader/Writer、随机读写文件、
File、NIO、序列化和反序列化。

**1. 流**

在Java中（很多其他语言也类似），文件一般不是单独处理的，
而是视为输入输出（Input/Output,IO）设备的一种。Java使用基本统一的概念处理所有的IO，
包括键盘、显示终端、网络等。

这个统一的概念是流，流有输入流和输出流之分。输入流就是可以从中获取数据，
输入流的实际提供者可以是键盘、文件、网络等；输出流就是可以向其中写入数据，
输出流的实际目的地可以是显示终端、文件、网络等。

**2. 装饰器设计模式**

基本的流按字节读写，没有缓冲区，这不方便使用。Java解决这个问题的方法是使用装饰器设计模式，
引入了很多装饰类，对基本的流增加功能，以方便使用。一般一个类只关注一个方面，实际使用时，
经常会需要多个装饰类。

Java中有很多装饰类，有两个基类：过滤器输入流FilterInputStream和过滤器输出流FilterOutputStream。

**3. Reader/Writer**

以InputStream/OutputStream为基类的流基本都是以二进制形式处理数据的，
不能够方便地处理文本文件，没有编码的概念，
能够方便地按字符处理文本数据的基类是Reader和Writer，它也有很多子类。

**4. 随机读写文件**

大部分情况下，使用流或Reader/Writer读写文件内容，
但Java提供了一个独立的可以随机读写文件的类RandomAccessFile，适用于大小已知的记录组成的文件。
该类在日常应用开发中用得比较少，但在一些系统程序中用得比较多。

**5. File**

上面介绍的都是操作数据本身，而关于文件路径、文件元数据、文件目录、临时文件、访问权限管理等，
Java使用File这个类来表示。

**6. NIO**

Java还有一个关于IO操作的包java.nio, nio表示New IO，这个包下同样包含大量的类。

NIO代表一种不同的看待IO的方式，它有缓冲区和通道的概念。
利用缓冲区和通道往往可以达成和流类似的目的，不过，它们更接近操作系统的概念，
某些操作的性能也更高。

除了看待方式不同，NIO还支持一些比较底层的功能，
如内存映射文件、文件加锁、自定义文件系统、非阻塞式IO、异步IO等。

不过，这些功能要么是比较底层，普通应用程序用到得比较少，要么主要适用于网络IO操作。

**7. 序列化和反序列化**

序列化就是将内存中的Java对象持久保存到一个流中，反序列化就是从流中恢复Java对象到内存。
序列化和反序列化主要有两个用处：一是对象状态持久化，二是网络远程调用，用于传递和返回对象。

Java主要通过接口Serializable和类ObjectInputStream/ObjectOutputStream提供对序列化的支持，
基本的使用是比较简单的，但也有一些复杂的地方。不过，Java的默认序列化有一些缺点，
比如，序列化后的形式比较大、浪费空间，序列化/反序列化的性能也比较低，更重要的问题是，
它是Java特有的技术，不能与其他语言交互。

文件看起来是一件非常简单的事情，但实际却没有那么简单，Java的设计也不是太完美，
包含了大量的类，这使得对于文件的理解变得困难。

#### 13.2 二进制文件和字节流

以二进制方式读写的主要流有：   
- InputStream/OutputSteam：基类，抽象类
- FileInputStream/FileOutputStream：文件
- ByteArrayInputStream/ByteArrayOutputStream：字节数组
- DataInputStream/DataOutputStream：装饰类，基本类型和字符串
- BufferedInputStream/BufferedOutputStream：装饰类，提供缓冲

##### 13.2.1 InputStream/OutputStream

**1. InputStream**

```java
/**
 * 看源码注释
 */
public abstract class InputStream implements Closeable {

    /**
     * 一次读一个字节，抽象方法，具体字类实现
     * @return int 0～255
     * @throws IOException
     */
    public abstract int read() throws IOException;

    /**
     * 一次读多个字节
     * @param b
     * @return int 实际读入的字节个数
     * @throws IOException
     */
    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        // ...
    }

    public long skip(long n) throws IOException {
        // ...
    }
    
    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {}

    public synchronized void mark(int readlimit) {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}
```

**2. OutputStream**

```java
public abstract class OutputStream implements Closeable, Flushable {
    /**
     * 向流中写入一个字节
     * @param b 虽然是 int，但只会用到最低的8位
     * @throws IOException
     */
    public abstract void write(int b) throws IOException;

    /**
     * 批量写入，向流中写入多个字节
     * @param b
     * @throws IOException
     */
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte b[], int off, int len) throws IOException {
        // ...
    }

    /**
     * 调用flush方法没有任何效果，数据只是传递给了操作系统，
     * 但操作系统什么时候保存到硬盘上，这是不一定的。
     * @throws IOException
     */
    public void flush() throws IOException {
    }

    public void close() throws IOException {
    }
}
```
















