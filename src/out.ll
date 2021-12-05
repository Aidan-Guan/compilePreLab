declare i32 @putch(i32)
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = add i32 0, 48
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 1
	store i32 %x4, i32* %x3
	br label %block0

block0:
	%x5 = load i32, i32* %x3
	%x6 = add i32 0, 12
	%x7 = icmp slt i32 %x5, %x6
	br i1 %x7, label %block1, label %block2

block1:
	%x8 = alloca i32
	%x9 = add i32 0, 0
	store i32 %x9, i32* %x8
	br label %block3

block3:
	%x10 = add i32 0, 1
	%x11 = add i32 0, 1
	%x12 = icmp eq i32 %x10, %x11
	br i1 %x12, label %block4, label %block5

block4:
	%x13 = load i32, i32* %x8
	%x14 = add i32 0, 3
	%x15 = srem i32 %x13, %x14
	%x16 = add i32 0, 1
	%x17 = icmp eq i32 %x15, %x16
	br i1 %x17, label %block6, label %block7

block6:
	%x18 = load i32, i32* %x1
	%x19 = add i32 0, 1
	%x20 = add i32 %x18, %x19
	%x21 = call i32 @putch(i32 %x20)
	br label %block8

block7:
	%x22 = load i32, i32* %x1
	%x23 = call i32 @putch(i32 %x22)
	br label %block8

block8:
	%x24 = load i32, i32* %x8
	%x25 = add i32 0, 1
	%x26 = add i32 %x24, %x25
	store i32 %x26, i32* %x8
	%x27 = load i32, i32* %x8
	%x28 = add i32 0, 2
	%x29 = load i32, i32* %x3
	%x30 = mul i32 %x28, %x29
	%x31 = add i32 0, 1
	%x32 = sub i32 %x30, %x31
%x33 = icmp sge i32 %x27, %x32
	br i1 %x33, label %block9, label %block10

block9:
	br label %block5
	br label %block10

block10:

block5:
	br label %block3
	%x34 = add i32 0, 10
	%x35 = call i32 @putch(i32 %x34)
	%x36 = load i32, i32* %x3
	%x37 = add i32 0, 1
	%x38 = add i32 %x36, %x37
	store i32 %x38, i32* %x3
	br label %block0

block2:
	br label %block0
	%x39 = add i32 0, 0
	ret i32 %x39
}