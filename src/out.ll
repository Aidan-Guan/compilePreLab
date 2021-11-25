declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = alloca i32
	%x3 = alloca i32
	%x4 = add i32 0, 1
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = alloca i32
	%x7 = load i32, i32* %x1
	%x8 = load i32, i32* %x2
	%x9 = add i32 %x7, %x8
	%x10 = add i32 0, 9
	%x11 = icmp eq i32 %x9, %x10
	br i1 %x11, label %block0, label %block1

block2:
	%x12 = load i32, i32* %x1
	%x13 = load i32, i32* %x2
	%x14 = sub i32 %x12, %x13
	%x15 = add i32 0, 0
	%x16 = icmp eq i32 %x14, %x15
	br i1 %x16, label %block3, label %block1

block3:
	%x17 = load i32, i32* %x6
	%x18 = add i32 0, 4
	%x19 = icmp ne i32 %x17, %x18
	br i1 %x19, label %block0, label %block1

block0:
	br label %block4

block1:
	%x20 = load i32, i32* %x3
	%x21 = load i32, i32* %x5
	%x22 = add i32 %x20, %x21
	%x23 = add i32 0, 1
	%x24 = sub i32 0, %x23
	%x25 = icmp ne i32 %x22, %x24
	br i1 %x25, label %block5, label %block6

block7:
	%x26 = load i32, i32* %x6
	%x27 = add i32 0, 1
	%x28 = add i32 %x26, %x27
	%x29 = add i32 0, 2
	%x30 = srem i32 %x28, %x29
	%x31 = add i32 0, 1
	%x32 = icmp eq i32 %x30, %x31
	br i1 %x32, label %block5, label %block6

block5:
	br label %block6

block6:
	br label %block4

block4:
	%x33 = call i32 @putint()
	%x34 = add i32 0, 0
	ret i32 %x34
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = alloca i32
	%x3 = alloca i32
	%x4 = add i32 0, 1
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = alloca i32
	%x7 = load i32, i32* %x1
	%x8 = load i32, i32* %x2
	%x9 = add i32 %x7, %x8
	%x10 = add i32 0, 9
	%x11 = icmp eq i32 %x9, %x10
	br i1 %x11, label %block0, label %block1

block2:
	%x12 = load i32, i32* %x1
	%x13 = load i32, i32* %x2
	%x14 = sub i32 %x12, %x13
	%x15 = add i32 0, 0
	%x16 = icmp eq i32 %x14, %x15
	br i1 %x16, label %block3, label %block1

block3:
	%x17 = load i32, i32* %x6
	%x18 = add i32 0, 4
	%x19 = icmp ne i32 %x17, %x18
	br i1 %x19, label %block0, label %block1

block0:
	br label %block4

block1:
	%x20 = load i32, i32* %x3
	%x21 = load i32, i32* %x5
	%x22 = add i32 %x20, %x21
	%x23 = add i32 0, 1
	%x24 = sub i32 0, %x23
	%x25 = icmp ne i32 %x22, %x24
	br i1 %x25, label %block5, label %block6

block7:
	%x26 = load i32, i32* %x6
	%x27 = add i32 0, 1
	%x28 = add i32 %x26, %x27
	%x29 = add i32 0, 2
	%x30 = srem i32 %x28, %x29
	%x31 = add i32 0, 1
	%x32 = icmp eq i32 %x30, %x31
	br i1 %x32, label %block5, label %block6

block5:
	br label %block6

block6:
	br label %block4

block4:
	%x33 = call i32 @putint()
	%x34 = add i32 0, 0
	ret i32 %x34
}