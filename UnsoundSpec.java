class UnsoundSpec {
  static class Constrain<A, B extends A> {}
  static <A,B extends A>
  A upcast(Constrain<A,B> constrain, B b) {
    return b;
  }
  static <T,U> U coerce(T t) {
    Constrain<U,? super T> constrain = null;
    return upcast(constrain, t);
  }
  public static void main(String[] args) {
    String zero = coerce(0);
  }
}
