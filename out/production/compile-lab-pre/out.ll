declare i32 @putch(i32)
declare i32 @getint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = call i32 @getint()
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 65
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 67
	store i32 %x6, i32* %x5
	%x7 = alloca i32
	%x8 = add i32 0, 87
	store i32 %x8, i32* %x7
	%x9 = alloca i32
	%x10 = add i32 0, 10
	store i32 %x10, i32* %x9
	%x11 = add i32 0, 1
	%x12 = add i32 0, 2
	%x13 = icmp sgt i32 %x11, %x12
	br i1 %x13, label %block0, label %block1

block0:
	br label %block2

block2:
	%x14 = add i32 0, 1
	%x15 = add i32 0, 1
	%x16 = icmp eq i32 %x14, %x15
	br i1 %x16, label %block3, label %block4

block3:
	br label %block2

block4:
	br label %block1

block1:
	%x17 = add i32 0, 1
	%x18 = add i32 0, 2
	%x19 = icmp slt i32 %x17, %x18
	br i1 %x19, label %block5, label %block6

block5:
	%x20 = alloca i32
	%x21 = add i32 0, 1
	store i32 %x21, i32* %x20
	br label %block8

block8:
	%x22 = load i32, i32* %x20
	%x23 = load i32, i32* %x1
	%x24 = icmp slt i32 %x22, %x23
	br i1 %x24, label %block9, label %block10

block9:
	%x25 = load i32, i32* %x3
	%x26 = call i32 @putch(i32 %x25)
	%x27 = add i32 0, 67
	%x28 = call i32 @putch(i32 %x27)
	%x29 = load i32, i32* %x9
	%x30 = call i32 @putch(i32 %x29)
	%x31 = load i32, i32* %x20
	%x32 = add i32 0, 1
	%x33 = add i32 %x31, %x32
	store i32 %x33, i32* %x20
	br label %block8

block10:
	br label %block7

block6:
	%x34 = alloca i32
	%x35 = add i32 0, 1
	store i32 %x35, i32* %x34
	br label %block11

block11:
	%x36 = load i32, i32* %x34
	%x37 = load i32, i32* %x1
	%x38 = icmp slt i32 %x36, %x37
	br i1 %x38, label %block12, label %block13

block12:
	%x39 = load i32, i32* %x7
	%x40 = call i32 @putch(i32 %x39)
	%x41 = load i32, i32* %x3
	%x42 = call i32 @putch(i32 %x41)
	%x43 = load i32, i32* %x9
	%x44 = call i32 @putch(i32 %x43)
	br label %block11

block13:
	br label %block7

block7:
	%x45 = alloca i32
	%x46 = add i32 0, 1
	store i32 %x46, i32* %x45
	br label %block14

block14:
	%x47 = load i32, i32* %x45
	%x48 = load i32, i32* %x1
	%x49 = icmp sle i32 %x47, %x48
	br i1 %x49, label %block15, label %block16

block15:
	%x50 = load i32, i32* %x3
	%x51 = call i32 @putch(i32 %x50)
	%x52 = load i32, i32* %x5
	%x53 = call i32 @putch(i32 %x52)
	%x54 = load i32, i32* %x45
	%x55 = add i32 0, 1
	%x56 = add i32 %x54, %x55
	store i32 %x56, i32* %x45
	br label %block14

block16:
	%x57 = add i32 0, 0
	ret i32 %x57
}define dso_local i32 @main(){
	br label %block0

block0:
	%x1 = add i32 0, 1
	%x2 = add i32 0, 1
	%x3 = icmp eq i32 %x1, %x2
	br i1 %x3, label %block1, label %block2

block1:
	br label %block0

block2:
	%x4 = add i32 0, 0
	ret i32 %x4
}