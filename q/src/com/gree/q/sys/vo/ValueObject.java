package com.gree.q.sys.vo;
import java.io.Serializable;
public class ValueObject
    implements Serializable {
  private boolean isNull = true;
  public ValueObject() {
  }
  public void setIsNull(boolean isNull) {
    this.isNull = isNull;
  }
  public boolean isNull() {
    return isNull;
  }
}
