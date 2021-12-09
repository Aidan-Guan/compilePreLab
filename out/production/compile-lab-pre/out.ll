declare void @putch(i32)
declare void @putint(i32)
@a = dso_local global [3 x [4 x i32]] zeroinitializer
define dso_local i32 @main(){
	%x2 = alloca i32
	%x3 = add i32 0, 0
	store i32 %x3, i32* %x2
	%x4 = alloca i32
	%x5 = add i32 0, 0
	store i32 %x5, i32* %x4
	br label %block1

block1:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 3
	%x8 = add i32 0, 4
	%x9 = add i32 %x7, %x8
	%x10 = add i32 0, 2
	%x11 = sub i32 %x9, %x10
	%x12 = icmp sle i32 %x6, %x11
	br i1 %x12, label %block2, label %block3

block2:
	%x13 = alloca i32
	%x14 = load i32, i32* %x2
	store i32 %x14, i32* %x13
	br label %block4

block4:
	%x15 = load i32, i32* %x13
	%x16 = add i32 0, 0
	%x17 = icmp sge i32 %x15, %x16
	br i1 %x17, label %block5, label %block6

block5:
	%x18 = load i32, i32* %x13
	%x19 = add i32 0, 4
	%x20 = icmp slt i32 %x18, %x19
	br i1 %x20, label %block9, label %block8

block9:
	%x21 = load i32, i32* %x2
	%x22 = load i32, i32* %x13
	%x23 = sub i32 %x21, %x22
	%x24 = add i32 0, 3
	%x25 = icmp slt i32 %x23, %x24
	br i1 %x25, label %block7, label %block8

block7:
	%x26 = load i32, i32* %x2
	%x27 = load i32, i32* %x13
	%x28 = sub i32 %x26, %x27
	%x29 = load i32, i32* %x13
	%x30 = getelementptr  [3 x [4 x i32]],  [3 x [4 x i32]]* @a, i32 0, i32 %x28, i32 %x29
	%x31 = load i32, i32* %x4
	store i32 %x31, i32* %x30
	%x32 = load i32, i32* %x4
	%x33 = add i32 0, 1
	%x34 = add i32 %x32, %x33
	store i32 %x34, i32* %x4
	br label %block8

block8:
	%x35 = load i32, i32* %x13
	%x36 = add i32 0, 1
	%x37 = sub i32 %x35, %x36
	store i32 %x37, i32* %x13
	br label %block4

block6:
	%x38 = load i32, i32* %x2
	%x39 = add i32 0, 1
	%x40 = add i32 %x38, %x39
	store i32 %x40, i32* %x2
	br label %block1

block3:
	%x41 = add i32 0, 0
	store i32 %x41, i32* %x2
	%x42 = alloca i32
	%x43 = add i32 0, 0
	store i32 %x43, i32* %x42
	br label %block10

block10:
	%x44 = load i32, i32* %x2
	%x45 = add i32 0, 3
	%x46 = icmp slt i32 %x44, %x45
	br i1 %x46, label %block11, label %block12

block11:
	%x47 = add i32 0, 0
	store i32 %x47, i32* %x42
	br label %block13

block13:
	%x48 = load i32, i32* %x42
	%x49 = add i32 0, 4
	%x50 = icmp slt i32 %x48, %x49
	br i1 %x50, label %block14, label %block15

block14:
	%x51 = load i32, i32* %x2
	%x52 = load i32, i32* %x42
	%x53 = getelementptr  [3 x [4 x i32]],  [3 x [4 x i32]]* @a, i32 0, i32 %x51, i32 %x52
	%x54 = load i32, i32* %x53
	call void @putint(i32 %x54)
	%x55 = add i32 0, 32
	call void @putch(i32 %x55)
	%x56 = load i32, i32* %x42
	%x57 = add i32 0, 1
	%x58 = add i32 %x56, %x57
	store i32 %x58, i32* %x42
	br label %block13

block15:
	%x59 = add i32 0, 10
	call void @putch(i32 %x59)
	%x60 = load i32, i32* %x2
	%x61 = add i32 0, 1
	%x62 = add i32 %x60, %x61
	store i32 %x62, i32* %x2
	br label %block10

block12:
	%x63 = add i32 0, 0
	ret i32 %x63
}declare void @putint(i32)
@field = dso_local global [2 x i32] zeroinitializer
define dso_local i32 @main(){
	%x2 = add i32 0, 1
	%x3 = alloca [1 x i32]
	%x4 = add i32 0, 3
	%x5 = alloca [3 x i32]
	%x6 = alloca i32
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x7
declare i32 @getint()
@field = dso_local global [2 x i32] zeroinitializer
define dso_local i32 @main(){
	%x2 = add i32 0, 1
	%x3 = alloca [1 x i32]
	%x4 = add i32 0, 3
	%x5 = alloca [3 x i32]
	%x6 = alloca i32
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x7
	%x9 = call i32 @getint()
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 1
	%x11 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x10
	%x12 = call i32 @getint()
	store i32 %x12, i32* %x11
	%x13 = add i32 0, 0
	%x14 = add i32 0, 0
	%x15 = add i32 %x13, %x14
	%x16 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x15
	%x17 = add i32 0, 1
	%x18 = sub i32 0, %x17
	store i32 %x18, i32* %x16
	%x19 = add i32 0, 1
	%x20 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x19
	%x21 = add i32 0, 0
	%x22 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x21
	%x23 = load i32, i32* %x22
	%x24 = add i32 0, 2
	%x25 = sub i32 %x23, %x24
	store i32 %x25, i32* %x20
	%x26 = add i32 0, 1
	%x27 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x26
	%x28 = load i32, i32* %x27
	store i32 %x28, i32* %x6
	%x29 = add i32 0, 2
	%x30 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x29
	%x31 = add i32 0, 16
	store i32 %x31, i32* %x30
	%x32 = add i32 0, 3
	%x33 = add i32 0, 0
	%x34 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x33
	%x35 = load i32, i32* %x34
	%x36 = sub i32 %x32, %x35
	%x37 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x36
	%x38 = load i32, i32* %x37
	%x39 = add i32 0, 2
	%x40 = add i32 %x38, %x39
	%x41 = load i32, i32* %x6
	%x42 = add i32 %x40, %x41
	call void @putint(i32 %x42)
	%x43 = add i32 0, 0
	ret i32 %x43
}declare void @putint(i32)
@field = dso_local global [2 x i32] zeroinitializer
define dso_local i32 @main(){
	%x2 = add i32 0, 1
	%x3 = alloca [1 x i32]
	%x4 = add i32 0, 3
	%x5 = alloca [3 x i32]
	%x6 = alloca i32
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x7
declare i32 @getint()
@field = dso_local global [2 x i32] zeroinitializer
define dso_local i32 @main(){
	%x2 = add i32 0, 1
	%x3 = alloca [1 x i32]
	%x4 = add i32 0, 3
	%x5 = alloca [3 x i32]
	%x6 = alloca i32
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x7
	%x9 = call i32 @getint()
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 1
	%x11 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x10
	%x12 = call i32 @getint()
	store i32 %x12, i32* %x11
	%x13 = add i32 0, 0
	%x14 = add i32 0, 0
	%x15 = add i32 %x13, %x14
	%x16 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x15
	%x17 = add i32 0, 1
	%x18 = sub i32 0, %x17
	store i32 %x18, i32* %x16
	%x19 = add i32 0, 1
	%x20 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x19
	%x21 = add i32 0, 0
	%x22 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x21
	%x23 = load i32, i32* %x22
	%x24 = add i32 0, 2
	%x25 = sub i32 %x23, %x24
	store i32 %x25, i32* %x20
	%x26 = add i32 0, 1
	%x27 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x26
	%x28 = load i32, i32* %x27
	store i32 %x28, i32* %x6
	%x29 = add i32 0, 2
	%x30 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x29
	%x31 = add i32 0, 16
	store i32 %x31, i32* %x30
	%x32 = add i32 0, 3
	%x33 = add i32 0, 0
	%x34 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x33
	%x35 = load i32, i32* %x34
	%x36 = sub i32 %x32, %x35
	%x37 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x36
	%x38 = load i32, i32* %x37
	%x39 = add i32 0, 2
	%x40 = add i32 %x38, %x39
	%x41 = load i32, i32* %x6
	%x42 = add i32 %x40, %x41
	call void @putint(i32 %x42)
	%x43 = add i32 0, 0
	ret i32 %x43
}declare void @putint(i32)
declare i32 @getint()
@field = dso_local global [2 x i32] zeroinitializer
define dso_local i32 @main(){
	%x2 = add i32 0, 1
	%x3 = alloca [1 x i32]
	%x4 = add i32 0, 3
	%x5 = alloca [3 x i32]
	%x6 = alloca i32
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x7
	%x9 = call i32 @getint()
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 1
	%x11 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x10
	%x12 = call i32 @getint()
	store i32 %x12, i32* %x11
	%x13 = add i32 0, 0
	%x14 = add i32 0, 0
	%x15 = add i32 %x13, %x14
	%x16 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x15
	%x17 = add i32 0, 1
	%x18 = sub i32 0, %x17
	store i32 %x18, i32* %x16
	%x19 = add i32 0, 1
	%x20 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x19
	%x21 = add i32 0, 0
	%x22 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x21
	%x23 = load i32, i32* %x22
	%x24 = add i32 0, 2
	%x25 = sub i32 %x23, %x24
	store i32 %x25, i32* %x20
	%x26 = add i32 0, 1
	%x27 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x26
	%x28 = load i32, i32* %x27
	store i32 %x28, i32* %x6
	%x29 = add i32 0, 2
	%x30 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x29
	%x31 = add i32 0, 16
	store i32 %x31, i32* %x30
	%x32 = add i32 0, 3
	%x33 = add i32 0, 0
	%x34 = getelementptr  [2 x i32],  [2 x i32]* @field, i32 0, i32 %x33
	%x35 = load i32, i32* %x34
	%x36 = sub i32 %x32, %x35
	%x37 = getelementptr  [3 x i32],  [3 x i32]* %x5, i32 0, i32 %x36
	%x38 = load i32, i32* %x37
	%x39 = add i32 0, 2
	%x40 = add i32 %x38, %x39
	%x41 = load i32, i32* %x6
	%x42 = add i32 %x40, %x41
	call void @putint(i32 %x42)
	%x43 = add i32 0, 0
	ret i32 %x43
}