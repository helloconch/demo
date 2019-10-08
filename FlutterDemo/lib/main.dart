// void main()=>runApp(new CalcWidget());

void main() {
  print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  printInteger(100);
  print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  print((-1.8).abs());
  var name = 'cat';
  print('this is $name');
  print('this is ${name.toUpperCase()}');

  var arr1 = ["T1", "T2", "T3"];
  arr1.add("T4");
  print(arr1);

  var arr2 = List.of(arr1);
  print(arr2);

  arr2.forEach((f) => print('arr2 item :$f'));

  print("arr2 is list:${arr2 is List<String>}");

  var map1 = {"name": 'Tome', "age": 10};

  map1.forEach((k, v) => print('k:$k --- v:$v'));

  var map2 = new Map<String, String>();

  map2["name"] = 'zhang';
  map2['sex'] = '女';
  map2.forEach((k, v) => print('k:$k -- v:$v'));

  print("map2 is Map:${map2 is Map<String, String>}");

  int b;
  bool f;
  print(b);
  print(f);

  Function fc = isZero;
  int x = 10;
  int y = 0;
  printInfo(x, fc);
  printInfo(y, isZero);

  enable1Flags(bold: true, hidden: false);
  enable1Flags(bold: true);
  enable2Flags(bold: false);
  enable3Flags(true);

  print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

  var point = Point(100, 200);
  point.printInfo();
  Point.factor = 10;
  Point.printZValue();

  var point2 = Point2.bottom(100);
  point2.printInfo();

  var v1 = V1();
  v1
    ..x = 1
    ..y = 1
    ..z = 1;
  v1.printInfo();
  var v2 = V2();
  v2
    ..x = 100
    ..y = 200;
  v2.printInfo();

  print(v1 is Point3);
  print(v2 is Point3);

  var v3 = V3();

  print('v3 is Point3:${v3 is Point3}');

  final vector1 = Vector(3, 3);

  final vector2 = Vector(2, 2);

  final vector3 = Vector(1, 1);

  print('vector ${vector1==(vector2+vector3)}');
}

printInteger(int index) {
  print('hello flutter index:$index');
}

bool isZero(int number) {
  return number == 0;
}

void printInfo(int number, Function check) {
  print('$number is Zero: ${check(number)}');
}

void enable1Flags({bool bold, bool hidden}) => print('$bold,$hidden');
void enable2Flags({bool bold = true, bool hidden = false}) =>
    print('$bold,$hidden');
void enable3Flags(bool bold, [bool hidden]) => print('$bold,$hidden');
void enable4Flags(bool bold, [bool hidden = false]) => print('$bold,$hidden');

class Point {
  num x, y;
  static num factor = 0;
  Point(this.x, this.y);
  void printInfo() => print('($x,$y)');
  static void printZValue() => print('$factor');
}

class Point2 {
  num x, y, z;
  //初始化变量z
  Point2(this.x, this.y) : z = 0;
  //重定向构造函数
  Point2.bottom(num x) : this(x, 0);
  void printInfo() => print('($x,$y,$z)');
}

class Point3 {
  num x, y;

  void printInfo() => print('$x,$y');
}

class V1 extends Point3 {
  num z = 0;

  @override
  void printInfo() {
    print('$x,$y,$z');
  }
}

class V2 implements Point3 {
  num x;
  num y;

  @override
  void printInfo() {
    print('$x,$y');
  }
}

class V3 with Point3 {}

class Vector {
  num x, y;
  Vector(this.x, this.y);
  //自定义相加运算符，实现向量相加
  Vector operator +(Vector v) => Vector(x + v.x, y + v.y);
  //覆写相等运算符，判断向量相等
  bool operator ==(dynamic v) => x == v.x && y == v.y;
}
