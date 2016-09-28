class Nullless<T, U> {
  class Constrain<B extends U> {}
  final Constrain<? super T> constrain;
  final U u;
  Nullless(T t) {
    u = coerce(t);
    constrain = getConstrain();
  }
  <B extends U>
  U upcast(Constrain<B> constrain, B b) {
    return b;
  }
  U coerce(T t) {
    return upcast(constrain, t);
  }
  Constrain<? super T> getConstrain() {
    return constrain;
  }
  public static void main(String[] args) {
    String zero = new Nullless<Integer,String>(0).u;
  }
}
