declare i32 @putch(i32)
declare i32 @putint(i32)
declare i32 @getint()
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
	br label %block0

block0:
	%x7 = load i32, i32* %x3
	%x8 = load i32, i32* %x1
	%x9 = icmp slt i32 %x7, %x8
	br i1 %x9, label %block1, label %block2

block1:
	%x10 = load i32, i32* %x3
	%x11 = add i32 0, 1
	%x12 = add i32 %x10, %x11
	store i32 %x12, i32* %x3
	%x13 = load i32, i32* %x5
	%x14 = load i32, i32* %x3
	%x15 = add i32 %x13, %x14
	store i32 %x15, i32* %x5
	%x16 = load i32, i32* %x5
	%x17 = call i32 @putint(i32 %x16)
	%x18 = add i32 0, 10
	%x19 = call i32 @putch(i32 %x18)
	br label %block0

block2:
	%x20 = add i32 0, 0
	ret i32 %x20
}