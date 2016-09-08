class Unsound9<U,T> {
  static class Type<A> {
    class Constraint<B extends A> extends Type<B> {}
    <B> Constraint<? super B> bad() { return null; }
    <B> A coerce(B b) {
      return pair(this.<B>bad(), b).value;
    }
  }
  static class DependentSum<T> {
    Type<T> type;
    T value;
    DependentSum(Type<T> t, T v) {
      type = t;
      value = v;
    }
  }
  static <T> DependentSum<T> pair(Type<T> type, T value) {
    return new DependentSum<T>(type, value);
  }
  static <T,U> U coerce(T t) {
    Type<U> type = new Type<U>();
    return type.<T>coerce(t);
  }
  public static void main(String[] args) {
    String zero = Unsound9.<Integer,String>coerce(0);
  }
}
