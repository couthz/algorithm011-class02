### 数组、链表、跳表总结

* 数组：
  * 实现：数组会开辟一段连续地址空间，每一个地址可以通过内存管理直接访问
  * 各种操作的时间复杂度：
    * 头插入：O(1) (这个需要在一开始为数组头部预留一部分空间)
    * 尾插入：O(1)
    * 插入：O(n)
    * 删除：O(n)
    * 查找：O(1)
* 链表
  * 实现：链表中每个节点的地址可以不连续，每个节点会有一个next指针指向下一个节点
  * 各种操作的时间复杂度：
    * 头插入：O(1) 
    * 尾插入：O(1)
    * 插入：O(1)
    * 删除：O(1)
    * 查找：O(n)
* 数组链表的比较
  * 修改和删除操作比较频繁的情况下，使用LInkedList更高效。如果查找操作居多，应使用Array
  * 保存同一组元素，LInkedList需要的内存更大
* 跳表
  * 实现：在有序链表的前提下，增加维度，每一维可以跨越更多的元素
  * 各种操作的时间复杂度(如果最高层索引正好跨越一半元素)
    * 头插入：O(logn) 
    * 尾插入：O(logn)
    * 插入：O(logn)
    * 删除：O(logn)
    * 查找：O(logn)

### 栈 队列总结

* 普通的栈与队列：栈是先进后出，队列是先进先出，添加和删除操作的时间复杂度都为O(1)，查询时间复杂度为O(n)。
* 双端队列：实战中更经常用双端队列，像是栈和队列的结合体，两端都可以进行压入和弹出的操作。
* 优先队列：插入操作的时间复杂度是O(1)，取出操作的时间复杂度是O(logN)，底层数据结构较为多样和复杂，可以是heap（即使是heap也可以用很多方式实现），bst，avl，红黑树，treap。

### 课后作业

用add first或add last改写Deque的实示例代码:

```java
Deque<String> deque = new LinkedList<String>();

deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");
System.out.println(deque);

String str = deque.peek();
System.out.println(str);
System.out.println(deque);

while (deque.size() > 0) {
	System.out.println(deque.removeFirst());
}

System.out.println(deque);
```

Deque接口的源码分析

Java中Deque为一个接口，常见的实现类有：LinkedList、ArrayDeque、PriorityDeque。

```
public interface Queue<E> extends Collection<E> {
    // 1.这组API会抛出异常
    // 向队尾插入一个元素
    boolean add(E e);
    // 移除队头一个元素
    E remove();
    // 输出队头元素
    E element();
    
    // 2.这组API会返回默认值
    // 向队尾插入一个元素
    boolean offer(E e);
    // 移除队头一个元素
    E poll();
    // 输出队头元素
    E peek();
}
```

PriorityQueue源码分析

JDK8源码中的PriorityQueue基于小根堆实现，同时表现为一个平衡二叉树。

其中添加元素的源码分析如下：

```java
public boolean add(E e) {
    return offer(e);
}

public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    // 结构性修改次数+1,在迭代器遍历元素的过程中如果结构发生改变，会触发Fail-Fast机制
    modCount++;
    int i = size;
    // 存储数组长度不够，触发扩容
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    // 第一次插入元素时如果队列中无元素，将设置下标0位置为新元素
    if (i == 0)
        queue[0] = e;
    else
        // 添加到队列中
        siftUp(i, e);
    return true;
}

/**
 * 添加到队列中，对应上浮操作
 * @param k 队列中元素个数
 * @param x 新增元素
 */
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}

/**
 * 使用自然排序排列
 * 插入元素时，先将新增元素放在最后一个元素位置，然后将新增元素与父级元素比较，如果大于等于父级元素，新增元素便插入在最后一个位置；否则循环向上查找父节点，直到新增元素不小于父节点
 * @param k 队列中总元素个数
 * @param x 新增元素
 */
@SuppressWarnings("unchecked")
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        // 找到索引为k的节点的父节点
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        // 新增节点大于等于父节点，不需要再调整
        if (key.compareTo((E) e) >= 0)
            break;
        // 将k位置设置为父节点
        queue[k] = e;
        // 搜索节点上浮
        k = parent;
    }
    // 上浮到k
    queue[k] = key;
}

@SuppressWarnings("unchecked")
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
```



