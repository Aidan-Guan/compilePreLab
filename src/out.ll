declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = alloca i32
	%x3 = load i32, i32* %x1
	%x4 = add i32 0, 5
	%x5 = icmp eq i32 %x3, %x4
	br i1 %x5, label %block0, label %block1

block0:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 10
	%x8 = icmp eq i32 %x6, %x7
	br i1 %x8, label %block2, label %block3

block2:
	br label %block4

block3:
	br label %block4

block4:
	br label %block1

block1:
	%x9 = call i32 @putint()
	%x10 = add i32 0, 0
	ret i32 %x10
}declare i32 @putint()
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = alloca i32
	%x3 = load i32, i32* %x1
	%x4 = add i32 0, 5
	%x5 = icmp eq i32 %x3, %x4
	br i1 %x5, label %block0, label %block1

block0:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 10
	%x8 = icmp eq i32 %x6, %x7
	br i1 %x8, label %block2, label %block3

block2:
	br label %block4

block3:
	br label %block4

block4:
	br label %block1

block1:
	%x9 = call i32 @putint()
	%x10 = add i32 0, 0
	ret i32 %x10
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
}