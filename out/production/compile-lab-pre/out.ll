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
	%x10 = load i32, i32* %x8
	%x11 = add i32 0, 2
	%x12 = load i32, i32* %x3
	%x13 = mul i32 %x11, %x12
	%x14 = add i32 0, 1
	%x15 = sub i32 %x13, %x14
	%x16 = icmp slt i32 %x10, %x15
	br i1 %x16, label %block4, label %block5

block4:
	%x17 = load i32, i32* %x8
	%x18 = add i32 0, 3
	%x19 = srem i32 %x17, %x18
	%x20 = add i32 0, 1
	%x21 = icmp eq i32 %x19, %x20
	br i1 %x21, label %block6, label %block7

block6:
	%x22 = load i32, i32* %x1
	%x23 = add i32 0, 1
	%x24 = add i32 %x22, %x23
	%x25 = call i32 @putch(i32 %x24)
	br label %block8

block7:
	%x26 = load i32, i32* %x1
	%x27 = call i32 @putch(i32 %x26)
	br label %block8

block8:
	%x28 = load i32, i32* %x8
	%x29 = add i32 0, 1
	%x30 = add i32 %x28, %x29
	store i32 %x30, i32* %x8
	br label %block3

block5:
	%x31 = add i32 0, 10
	%x32 = call i32 @putch(i32 %x31)
	%x33 = load i32, i32* %x3
	%x34 = add i32 0, 1
	%x35 = add i32 %x33, %x34
	store i32 %x35, i32* %x3
	br label %block0

block2:
	%x36 = add i32 0, 0
	ret i32 %x36
}