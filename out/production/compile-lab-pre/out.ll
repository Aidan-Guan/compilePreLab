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
}