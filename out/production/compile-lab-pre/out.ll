declare i32 @putch(i32)
declare i32 @putint(i32)
declare i32 @getch()
@ascii_0 = dso_local global i32 48
define dso_local i32 @main(){
	%x2 = alloca i32
	%x3 = add i32 0, 0
	store i32 %x3, i32* %x2
	%x4 = alloca i32
	br label %block0

block0:
	%x5 = add i32 0, 1
	%x6 = icmp ne i32 %x5, 0
	br i1 %x6, label %block1, label %block2

block1:
	%x7 = call i32 @getch()
	%x8 = load i32, i32* @ascii_0
	%x9 = sub i32 %x7, %x8
	store i32 %x9, i32* %x4
	%x10 = load i32, i32* %x4
	%x11 = add i32 0, 0
	%x12 = icmp slt i32 %x10, %x11
	br i1 %x12, label %block3, label %block4

block5:
	%x13 = load i32, i32* %x4
	%x14 = add i32 0, 9
	%x15 = icmp sgt i32 %x13, %x14
	br i1 %x15, label %block3, label %block4

block3:
	br label %block0

block4:
	br label %block2
	br label %block0

block2:
	%x16 = load i32, i32* %x4
	store i32 %x16, i32* %x2
	br label %block6

block6:
	%x17 = add i32 0, 1
	%x18 = icmp ne i32 %x17, 0
	br i1 %x18, label %block7, label %block8

block7:
	%x19 = call i32 @getch()
	%x20 = load i32, i32* @ascii_0
	%x21 = sub i32 %x19, %x20
	store i32 %x21, i32* %x4
	%x22 = load i32, i32* %x4
	%x23 = add i32 0, 0
%x24 = icmp sge i32 %x22, %x23
	br i1 %x24, label %block11, label %block10

block11:
	%x25 = load i32, i32* %x4
	%x26 = add i32 0, 9
	%x27 = icmp sle i32 %x25, %x26
	br i1 %x27, label %block9, label %block10

block9:
	%x28 = load i32, i32* %x2
	%x29 = add i32 0, 10
	%x30 = mul i32 %x28, %x29
	%x31 = load i32, i32* %x4
	%x32 = add i32 %x30, %x31
	store i32 %x32, i32* %x2
	br label %block0

block10:
	br label %block8
	br label %block6

block8:
	%x33 = load i32, i32* %x2
	%x34 = call i32 @putint(i32 %x33)
	%x35 = add i32 0, 10
	%x36 = call i32 @putch(i32 %x35)
	%x37 = add i32 0, 0
	store i32 %x37, i32* %x2
	br label %block12

block12:
	%x38 = add i32 0, 1
	%x39 = icmp ne i32 %x38, 0
	br i1 %x39, label %block13, label %block14

block13:
	%x40 = call i32 @getch()
	%x41 = load i32, i32* @ascii_0
	%x42 = sub i32 %x40, %x41
	store i32 %x42, i32* %x4
	%x43 = load i32, i32* %x4
	%x44 = add i32 0, 0
	%x45 = icmp slt i32 %x43, %x44
	br i1 %x45, label %block15, label %block16

block17:
	%x46 = load i32, i32* %x4
	%x47 = add i32 0, 9
	%x48 = icmp sgt i32 %x46, %x47
	br i1 %x48, label %block15, label %block16

block15:
	br label %block12

block16:
	br label %block14
	br label %block12

block14:
	%x49 = load i32, i32* %x4
	store i32 %x49, i32* %x2
	br label %block18

block18:
	%x50 = add i32 0, 1
	%x51 = icmp ne i32 %x50, 0
	br i1 %x51, label %block19, label %block20

block19:
	%x52 = call i32 @getch()
	%x53 = load i32, i32* @ascii_0
	%x54 = sub i32 %x52, %x53
	store i32 %x54, i32* %x4
	%x55 = load i32, i32* %x4
	%x56 = add i32 0, 0
%x57 = icmp sge i32 %x55, %x56
	br i1 %x57, label %block23, label %block22

block23:
	%x58 = load i32, i32* %x4
	%x59 = add i32 0, 9
	%x60 = icmp sle i32 %x58, %x59
	br i1 %x60, label %block21, label %block22

block21:
	%x61 = load i32, i32* %x2
	%x62 = add i32 0, 10
	%x63 = mul i32 %x61, %x62
	%x64 = load i32, i32* %x4
	%x65 = add i32 %x63, %x64
	store i32 %x65, i32* %x2
	br label %block0

block22:
	br label %block20
	br label %block18

block20:
	%x66 = load i32, i32* %x2
	%x67 = call i32 @putint(i32 %x66)
	%x68 = add i32 0, 0
	ret i32 %x68
}