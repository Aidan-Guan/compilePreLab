declare void @putint(i32)
@N = dso_local global i32 -1
@arr = dso_local global [6 x i32] [ i32 1, i32 2, i32 33, i32 4, i32 5, i32 6]
define dso_local i32 @main(){
	%x9 = alloca i32
	%x10 = add i32 0, 0
	store i32 %x10, i32* %x9
	%x11 = alloca i32
	%x12 = add i32 0, 0
	store i32 %x12, i32* %x11
	br label %block1

block1:
	%x13 = load i32, i32* %x9
	%x14 = add i32 0, 5
	%x15 = icmp slt i32 %x13, %x14
	br i1 %x15, label %block2, label %block3

block2:
	%x16 = load i32, i32* %x11
	%x17 = load i32, i32* %x9
	%x18 = getelementptr  [6 x i32],  [6 x i32]* @arr, i32 0, i32 %x17
	%x19 = load i32, i32* %x18
	%x20 = add i32 %x16, %x19
	store i32 %x20, i32* %x11
	%x21 = load i32, i32* %x9
	%x22 = add i32 0, 1
	%x23 = add i32 %x21, %x22
	store i32 %x23, i32* %x9
	br label %block1

block3:
	%x24 = load i32, i32* %x11
	call void @putint(i32 %x24)
	%x25 = add i32 0, 0
	ret i32 %x25
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%x2 = sub i32 0, %x1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @func1(){
	%x1 = add i32 0, 555
	ret i32 %x1
}
define dso_local i32 @func2(){
	%x1 = add i32 0, 111
	ret i32 %x1
}
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = call i32 @func1()
	store i32 %x2, i32* %x1
	%x3 = load i32, i32* %x1
	%x4 = call i32 @func2()
	%x5 = sub i32 %x3, %x4
	call void @putint(i32 %x5)
	%x6 = add i32 0, 0
	ret i32 %x6
}declare void @putint(i32)
define dso_local i32 @gcd(i32 %0, i32 %1){
	%x3 = alloca i32
	store i32 %x0, i32* %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = load i32, i32* %x4
	%x6 = add i32 0, 0
	%x7 = icmp eq i32 %x5, %x6
	br i1 %x7, label %block0, label %block1

block0:
	%x8 = load i32, i32* %x3
	ret i32 %x8

block1:
	%x9 = load i32, i32* %x4
	%x10 = load i32, i32* %x3
	%x11 = load i32, i32* %x4
	%x12 = srem i32 %x10, %x11
	%x13 = call i32 @gcd(i32 %x9, i32 %x12)
	ret i32 %x13
}
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 100
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 48
	store i32 %x4, i32* %x3
	%x5 = load i32, i32* %x1
	%x6 = load i32, i32* %x3
	%x7 = call i32 @gcd(i32 %x5, i32 %x6)
	call void @putint(i32 %x7)
	%x8 = add i32 0, 0
	ret i32 %x8
}declare void @putint(i32)
define dso_local i32 @sum2d([3 x i32]* %x0){
	%x2 = alloca [3 x i32]*
	store [3 x i32]* %x0, [3 x i32]* * %x2
	%x3 = alloca i32
	%x4 = add i32 0, 0
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	br label %block0

block0:
	%x7 = load i32, i32* %x3
	%x8 = add i32 0, 2
	%x9 = icmp slt i32 %x7, %x8
	br i1 %x9, label %block1, label %block2

block1:
	%x10 = alloca i32
	%x11 = add i32 0, 0
	store i32 %x11, i32* %x10
	br label %block3

block3:
	%x12 = load i32, i32* %x10
	%x13 = add i32 0, 3
	%x14 = icmp slt i32 %x12, %x13
	br i1 %x14, label %block4, label %block5

block4:
	%x15 = load i32, i32* %x5
	%x16 = load i32, i32* %x3
	%x17 = load i32, i32* %x10
	%x18 = load [3 x i32]*, [3 x i32]* * %x2
	%x19 = getelementptr [3 x i32], [3 x i32]* %x18, i32 %x16, i32 %x17
	%x20 = load i32, i32* %x19
	%x21 = add i32 %x15, %x20
	store i32 %x21, i32* %x5
	%x22 = load i32, i32* %x10
	%x23 = add i32 0, 1
	%x24 = add i32 %x22, %x23
	store i32 %x24, i32* %x10
	br label %block3

block5:
	%x25 = load i32, i32* %x3
	%x26 = add i32 0, 1
	%x27 = add i32 %x25, %x26
	store i32 %x27, i32* %x3
	br label %block0

block2:
	%x28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 3
	%x3 = alloca [2 x [3 x i32]]
	%x4 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 0, i32 1
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 0, i32 2
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 1, i32 0
	%x11 = add i32 0, 4
	store i32 %x11, i32* %x10
	%x12 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 1, i32 1
	%x13 = add i32 0, 5
	store i32 %x13, i32* %x12
	%x14 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* %x3, i32 0, i32 0
	%x15 = load i32, i32* %x14
	%x16 = call i32 @sum2d([3 x i32]* %x15)
	call void @putint(i32 %x16)
	%x17 = add i32 0, 0
	ret i32 %x17
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%x8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%x11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%x15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%x18 = load i32, i32* %x17
	%x19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%x21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%x24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%x25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%x28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%x16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%x20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%x21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%x8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%x11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%x15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%x18 = load i32, i32* %x17
	%x19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%x21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%x24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%x25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%x28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%x16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%x20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%x21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%18 = load i32, i32* %x17
	%19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%18 = load i32, i32* %x17
	%19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%18 = load i32, i32* %x17
	%19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%18 = load i32, i32* %x17
	%19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%16 = load i32, i32* %x15
	%x17 = add i32 0, 3
	%x18 = call i32 @_getMaxOfAll(i32* %x16, i32 %x17)
	%x19 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x18
	%20 = load i32, i32* %x19
	store i32 %x20, i32* %x14
	%21 = load i32, i32* %x14
	call void @putint(i32 %x21)
	%x22 = add i32 0, 0
	ret i32 %x22
}declare void @putint(i32)
define dso_local i32 @_getMaxOfAll(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 999999
	%x7 = sub i32 0, %x6
	store i32 %x7, i32* %x5
	%8 = load i32, i32* %x4
	%x9 = add i32 0, 1
	%x10 = sub i32 %x8, %x9
	store i32 %x10, i32* %x4
	br label %block0

block0:
	%11 = load i32, i32* %x4
	%x12 = add i32 0, 1
	%x13 = sub i32 0, %x12
	%x14 = icmp sgt i32 %x11, %x13
	br i1 %x14, label %block1, label %block2

block1:
	%15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x3
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%18 = load i32, i32* %x17
	%19 = load i32, i32* %x5
	%x20 = icmp sgt i32 %x18, %x19
	br i1 %x20, label %block3, label %block4

block3:
	%21 = load i32, i32* %x4
	%x22 = load i32*, i32* * %x3
	%x23 = getelementptr i32, i32* %x22, i32 %x21
	%24 = load i32, i32* %x23
	store i32 %x24, i32* %x5
	br label %block4

block4:
	%25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	store i32 %x27, i32* %x4
	br label %block0

block2:
	%28 = load i32, i32* %x5
	ret i32 %x28
}
define dso_local i32 @main(){
	%x1 = add i32 0, 3
	%x2 = alloca [3 x i32]
	%x3 = add i32 0, 0
	%x4 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x3
	%x5 = add i32 0, 2
	%x6 = sub i32 0, %x5
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 1
	%x8 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x7
	%x9 = add i32 0, 2
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x10
	%x12 = add i32 0, 7
	%x13 = sub i32 0, %x12
	store i32 %x13, i32* %x11
	%x14 = alloca i32
	%x15 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 0
	%x16 = add i32 0, 3
	%x17 = call i32 @_getMaxOfAll(i32* %x15, i32 %x16)
	%x18 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x17
	%19 = load i32, i32* %x18
	store i32 %x19, i32* %x14
	%20 = load i32, i32* %x14
	call void @putint(i32 %x20)
	%x21 = add i32 0, 0
	ret i32 %x21
}