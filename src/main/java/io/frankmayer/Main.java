package io.frankmayer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Main {
  private static final long[] llDelTimes = new long[10240];
  private static final long[] llInsTimes = new long[10240];
  private static final long[] aDelTimes = new long[10240];
  private static final long[] aInsTimes = new long[10240];
  private static final int size = 4096;

  public static void main(String[] args) {
    final var a = Main.createA(4);
    final var _69420 = new Obj(69420);
    final var _69 = new Obj(69);
    final var _420 = new Obj(420);
    final var _42 = new Obj(42);

    // warmup
    for (var i = 0; i < 1024; ++i) {
      final var ll = Main.createLL(Main.size);
      ll.remove(1000);
      ll.remove(69);
      ll.remove(420);
      ll.remove(2000);
      ll.remove(42);
      ll.remove(13);
      ll.remove(69);
      ll.remove(720);
      ll.remove(850);
      ll.add(1000, _69420);
      ll.add(69, _42);
      ll.add(420, _69);
      ll.add(2000, _69420);
      ll.add(42, _420);
      ll.add(13, _69);
      ll.add(69, _69420);
      ll.add(720, _42);
      ll.add(850, _420);
      final var arr = Main.createA(Main.size);
      Main.removeArrIdx(arr, 1000);
      Main.removeArrIdx(arr, 69);
      Main.removeArrIdx(arr, 420);
      Main.removeArrIdx(arr, 2000);
      Main.removeArrIdx(arr, 42);
      Main.removeArrIdx(arr, 13);
      Main.removeArrIdx(arr, 69);
      Main.removeArrIdx(arr, 720);
      Main.removeArrIdx(arr, 850);
      Main.insertArrIdx(arr, 1000, _69420);
      Main.insertArrIdx(arr, 69, _42);
      Main.insertArrIdx(arr, 420, _69);
      Main.insertArrIdx(arr, 2000, _69420);
      Main.insertArrIdx(arr, 42, _420);
      Main.insertArrIdx(arr, 13, _69);
      Main.insertArrIdx(arr, 69, _69420);
      Main.insertArrIdx(arr, 720, _42);
      Main.insertArrIdx(arr, 850, _420);
    }

    // actual test
    for (var i = 0; i < Main.llDelTimes.length; ++i) {
      final var ll = Main.createLL(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j != 10; ++j) {
        ll.remove(1000);
        ll.remove(69);
        ll.remove(420);
        ll.remove(2000);
        ll.remove(42);
        ll.remove(13);
        ll.remove(69);
        ll.remove(720);
        ll.remove(850);
      }
      final var end = System.nanoTime();
      Main.llDelTimes[i] = end - start;
    }
    for (var i = 0; i < Main.llInsTimes.length; ++i) {
      final var ll = Main.createLL(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j != 10; ++j) {
        ll.add(1000, _69420);
        ll.add(69, _42);
        ll.add(420, _69);
        ll.add(2000, _69420);
        ll.add(42, _420);
        ll.add(13, _69);
        ll.add(69, _69420);
        ll.add(720, _42);
        ll.add(850, _420);
      }
      final var end = System.nanoTime();
      Main.llInsTimes[i] = end - start;
    }

    Main.removeArrIdx(a, 2);
    Main.insertArrIdx(a, 2, _69420);

    for (var i = 0; i < Main.aDelTimes.length; ++i) {
      final var arr = Main.createA(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j != 10; ++j) {
        Main.removeArrIdx(arr, 1000);
        Main.removeArrIdx(arr, 69);
        Main.removeArrIdx(arr, 420);
        Main.removeArrIdx(arr, 2000);
        Main.removeArrIdx(arr, 42);
        Main.removeArrIdx(arr, 13);
        Main.removeArrIdx(arr, 69);
        Main.removeArrIdx(arr, 720);
        Main.removeArrIdx(arr, 850);
      }
      final var end = System.nanoTime();
      Main.aDelTimes[i] = end - start;
    }
    for (var i = 0; i < Main.aInsTimes.length; ++i) {
      final var arr = Main.createA(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j != 10; ++j) {
        Main.insertArrIdx(arr, 1000, _69420);
        Main.insertArrIdx(arr, 69, _42);
        Main.insertArrIdx(arr, 420, _69);
        Main.insertArrIdx(arr, 2000, _69420);
        Main.insertArrIdx(arr, 42, _420);
        Main.insertArrIdx(arr, 13, _69);
        Main.insertArrIdx(arr, 69, _69420);
        Main.insertArrIdx(arr, 720, _42);
        Main.insertArrIdx(arr, 850, _420);
      }
      final var end = System.nanoTime();
      Main.aInsTimes[i] = end - start;
    }

    System.out.printf("LinkedList delete: %s%n", Main.median(Main.llDelTimes));
    System.out.printf("LinkedList insert: %s%n", Main.median(Main.llInsTimes));
    System.out.printf("Array delete: %s%n", Main.median(Main.aDelTimes));
    System.out.printf("Array insert: %s%n", Main.median(Main.aInsTimes));
  }

  private static long median(final long[] arr) {
    Arrays.sort(arr);
    if ((arr.length & 0x1) == 0x1) {
      return arr[arr.length / 2];
    } else {
      final var a = arr[arr.length / 2 - 1];
      final var b = arr[arr.length / 2];
      return (a + b) / 2;
    }
  }

  private static LinkedList<Obj> createLL(final int size) {
    final var ll = new LinkedList<Obj>();
    final var r = new Random();
    for (var i = 0; i < size; ++i) {
      ll.add(new Obj(r.nextInt()));
    }
    return ll;
  }

  private static Obj[] createA(final int size) {
    final var a = new Obj[size];
    final var r = new Random();
    for (var i = 0; i < size; ++i) {
      a[i] = new Obj(r.nextInt());
    }
    return a;
  }

  private static void removeArrIdx(final Obj[] a, final int i) {
    final var ip1 = i + 1;
    System.arraycopy(a, ip1, a, i, a.length - ip1);
  }

  private static void insertArrIdx(Obj[] a, int i, Obj o) {
    final var ip1 = i + 1;
    System.arraycopy(a, i, a, ip1, a.length - ip1);
    a[i] = o;
  }
}
