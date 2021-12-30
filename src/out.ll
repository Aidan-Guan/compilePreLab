declare void @putint(i32)
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
	%x16 = add i32 0, 3
	%x17 = call i32 @_getMaxOfAll(i32* %x15, i32 %x16)
	%x18 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x17
	%x19 = load i32, i32* %x18
	store i32 %x19, i32* %x14
	%x20 = load i32, i32* %x14
	call void @putint(i32 %x20)
	%x21 = add i32 0, 0
	ret i32 %x21
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
	%x16 = add i32 0, 3
	%x17 = call i32 @_getMaxOfAll(i32* %x15, i32 %x16)
	%x18 = getelementptr  [3 x i32],  [3 x i32]* %x2, i32 0, i32 %x17
	%x19 = load i32, i32* %x18
	store i32 %x19, i32* %x14
	%x20 = load i32, i32* %x14
	call void @putint(i32 %x20)
	%x21 = add i32 0, 0
	ret i32 %x21
}