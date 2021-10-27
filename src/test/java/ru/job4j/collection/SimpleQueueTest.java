package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 2.1.3.List
 * 5. Очередь на двух стеках[#160 #127216]
 * Test.
 *
 * @author Dmitry
 * @version 1
 * @since 26.10.2021
 */
public class SimpleQueueTest {
    @Test
    public void whenPushThenPop() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
    }

    @Test
    public void whenPush12ThenPop1() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        assertThat(queue.poll(), is(1));
    }

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertThat(queue.poll(), is(2));
    }
}