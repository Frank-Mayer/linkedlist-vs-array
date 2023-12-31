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
    System.out.println("Warmup");
    try {
      for (var i = 0; i < 1024; ++i) {
        final var ll = Main.createLL(Main.size);
        for (int j = Main.size - 1; j > 0; j -= 4) {
          ll.remove(j);
        }
        for (int j = 0; j < Main.size - 4; j += 4) {
          ll.add(j, _69);
        }
        final var arr = Main.createA(Main.size);
        for (int j = Main.size - 1; j > 0; j -= 4) {
          Main.removeArrIdx(arr, j);
        }
        for (int j = 0; j < Main.size - 4; j += 4) {
          Main.insertArrIdx(arr, j, _69420);
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    // actual test
    System.out.println("Test");
    System.out.println("LinkedList delete");
    for (var i = 0; i < Main.llDelTimes.length; ++i) {
      final var ll = Main.createLL(Main.size);
      final var start = System.nanoTime();
      for (int j = Main.size - 1; j >= 0; j -= 4) {
        ll.remove(j);
      }
      final var end = System.nanoTime();
      Main.llDelTimes[i] = end - start;
    }
    System.out.println("LinkedList insert");
    for (var i = 0; i < Main.llInsTimes.length; ++i) {
      final var ll = Main.createLL(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j < Main.size; j += 4) {
        ll.add(j, _42);
      }
      final var end = System.nanoTime();
      Main.llInsTimes[i] = end - start;
    }

    System.out.println("Array delete");
    for (var i = 0; i < Main.aDelTimes.length; ++i) {
      final var arr = Main.createA(Main.size);
      final var start = System.nanoTime();
      for (int j = Main.size - 1; j >= 0; j -= 4) {
        Main.removeArrIdx(arr, j);
      }
      final var end = System.nanoTime();
      Main.aDelTimes[i] = end - start;
    }
    System.out.println("Array insert");
    for (var i = 0; i < Main.aInsTimes.length; ++i) {
      final var arr = Main.createA(Main.size);
      final var start = System.nanoTime();
      for (var j = 0; j < Main.size; j += 4) {
        Main.insertArrIdx(arr, j, _420);
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
