package io.frankmayer;

import java.util.concurrent.Semaphore;

public class Obj {
  public final int value;
  public final boolean a = false;
  public final float b = 3.14159265359f;
  public final double c = 3.14159265359d;
  public final String d = "3.14159265359";
  public final Object e = new Object();
  public final Semaphore f = new Semaphore(42, true);

  public Obj(final int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toHexString(this.value);
  }
}
