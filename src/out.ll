declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 10
	store i32 %x4, i32* %x3
	%x5 = load i32, i32* %x1
	%x6 = add i32 0, 6
	%x7 = icmp eq i32 %x5, %x6
	br i1 %x7, label %block0, label %block1

block2:
	%x8 = load i32, i32* %x3
	%x9 = add i32 0, 11
	%x10 = icmp eq i32 %x8, %x9
	br i1 %x10, label %block0, label %block1

block0:
	%x11 = load i32, i32* %x1
	ret i32 %x11

block1:
	%x12 = load i32, i32* %x3
	%x13 = add i32 0, 10
	%x14 = icmp eq i32 %x12, %x13
	br i1 %x14, label %block6, label %block5

block6:
	%x15 = load i32, i32* %x1
	%x16 = add i32 0, 1
	%x17 = icmp eq i32 %x15, %x16
	br i1 %x17, label %block4, label %block5

block4:
	%x18 = add i32 0, 25
	store i32 %x18, i32* %x1
	br label %block7

block5:
	%x19 = load i32, i32* %x3
	%x20 = add i32 0, 10
	%x21 = icmp eq i32 %x19, %x20
	br i1 %x21, label %block10, label %block9

block10:
	%x22 = load i32, i32* %x1
	%x23 = add i32 0, 5
	%x24 = sub i32 0, %x23
	%x25 = icmp eq i32 %x22, %x24
	br i1 %x25, label %block8, label %block9

block8:
	%x26 = load i32, i32* %x1
	%x27 = add i32 0, 15
	%x28 = add i32 %x26, %x27
	store i32 %x28, i32* %x1
	br label %block11

block9:
	%x29 = load i32, i32* %x1
	%x30 = sub i32 0, %x29
	store i32 %x30, i32* %x1
	br label %block11

block11:
	br label %block7

block7:
	br label %block3

block3:
	%x31 = call i32 @putint()
	%x32 = add i32 0, 0
	ret i32 %x32
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 10
	store i32 %x4, i32* %x3
	%x5 = load i32, i32* %x1
	%x6 = add i32 0, 5
	%x7 = icmp eq i32 %x5, %x6
	br i1 %x7, label %block0, label %block1

block0:
	%x8 = load i32, i32* %x3
	%x9 = add i32 0, 10
	%x10 = icmp eq i32 %x8, %x9
	br i1 %x10, label %block2, label %block3

block2:
	%x11 = add i32 0, 25
	store i32 %x11, i32* %x1
	br label %block4

block3:
	%x12 = load i32, i32* %x1
	%x13 = add i32 0, 15
	%x14 = add i32 %x12, %x13
	store i32 %x14, i32* %x1
	br label %block4

block4:
	br label %block1

block1:
	%x15 = call i32 @putint()
	%x16 = add i32 0, 0
	ret i32 %x16
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = call i32 @putint()
	%x4 = add i32 0, 0
	ret i32 %x4
}declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = load i32, i32* %x1
	%x4 = call i32 @putint(i32 %x3)
	%x5 = add i32 0, 0
	ret i32 %x5
}declare i32 @putch(i32)
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 5
	store i32 %x2, i32* %x1
	%x3 = load i32, i32* %x1
	%x4 = call i32 @putch(i32 %x3)
	%x5 = add i32 0, 0
	ret i32 %x5
}declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 1
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 0
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 1
	store i32 %x6, i32* %x5
	%x7 = alloca i32
	%x8 = add i32 0, 2
	store i32 %x8, i32* %x7
	%x9 = alloca i32
	%x10 = add i32 0, 4
	store i32 %x10, i32* %x9
	%x11 = alloca i32
	%x12 = add i32 0, 0
	store i32 %x12, i32* %x11
	%x13 = load i32, i32* %x1
	%x14 = load i32, i32* %x3
	%x15 = mul i32 %x13, %x14
	%x16 = load i32, i32* %x5
	%x17 = sdiv i32 %x15, %x16
	%x18 = load i32, i32* %x9
	%x19 = load i32, i32* %x7
	%x20 = add i32 %x18, %x19
	%x21 = icmp eq i32 %x17, %x20
	br i1 %x21, label %block3, label %block1

block3:
	%x22 = load i32, i32* %x1
	%x23 = load i32, i32* %x1
	%x24 = load i32, i32* %x3
	%x25 = add i32 %x23, %x24
	%x26 = mul i32 %x22, %x25
	%x27 = load i32, i32* %x5
	%x28 = add i32 %x26, %x27
	%x29 = load i32, i32* %x7
	%x30 = load i32, i32* %x9
	%x31 = add i32 %x29, %x30
%x32 = icmp sle i32 %x28, %x31
	br i1 %x32, label %block0, label %block1

block2:
	%x33 = load i32, i32* %x1
	%x34 = load i32, i32* %x3
	%x35 = load i32, i32* %x5
	%x36 = mul i32 %x34, %x35
	%x37 = sub i32 %x33, %x36
	%x38 = load i32, i32* %x7
	%x39 = load i32, i32* %x1
	%x40 = load i32, i32* %x5
	%x41 = sdiv i32 %x39, %x40
	%x42 = sub i32 %x38, %x41
	%x43 = icmp eq i32 %x37, %x42
	br i1 %x43, label %block0, label %block1

block0:
	%x44 = add i32 0, 1
	store i32 %x44, i32* %x11
	br label %block1

block1:
	%x45 = load i32, i32* %x11
	%x46 = call i32 @putint(i32 %x45)
	%x47 = add i32 0, 0
	ret i32 %x47
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @putint(i32)
@a = dso_local global i32 6
@b = dso_local global i32 7
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = load i32, i32* %x6
	%x9 = load i32, i32* %x4
	%x10 = add i32 %x8, %x9
	%x11 = call i32 @putint(i32 %x10)
	%x12 = add i32 0, 0
	ret i32 %x12
}declare i32 @getint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = call i32 @getint()
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 0
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	%x7 = add i32 0, 0
	ret i32 %x7
}