declare void @putch(i32)
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = mul i32 10, 5
	%x1 = sdiv i32 %x0, 2
	%x2 = alloca i32
	store i32 %x1, i32* %x2
	%x3 = load i32, i32* %x2
	%x4 = sdiv i32 %x3, 2
	%x5 = alloca i32
	store i32 %x4, i32* %x5
	%x6 = load i32, i32* %x2
	%x7 = mul i32 %x6, 2
	%x8 = alloca i32
	store i32 %x7, i32* %x8
	br label %x11

x9:
	%x21 = load i32, i32* %x2
	%x22 = sdiv i32 %x21, 4
	%x23 = alloca i32
	store i32 %x22, i32* %x23
	%x24 = load i32, i32* %x8
	%x25 = load i32, i32* %x23
	%x26 = sdiv i32 %x24, %x25
	call void @putint(i32 %x26)
	%x27 = load i32, i32* %x2
	%x28 = mul i32 %x27, 4
	%x29 = alloca i32
	store i32 %x28, i32* %x29
	%x30 = load i32, i32* %x29
	%x31 = load i32, i32* %x23
	%x32 = sdiv i32 %x30, %x31
	call void @putint(i32 %x32)
	call void @putch(i32 10)
	%x33 = load i32, i32* %x8
	%x34 = load i32, i32* %x5
	%x35 = sdiv i32 %x33, %x34
	call void @putint(i32 %x35)
	ret i32 0

x10:
	%x15 = alloca i32
	store i32 24, i32* %x15
	%x16 = load i32, i32* %x5
	%x17 = load i32, i32* %x15
	%x18 = load i32, i32* %x8
	%x19 = mul i32 %x17, %x18
	%x20 = sub i32 %x16, %x19
	call void @putint(i32 %x20)
	call void @putch(i32 10)
	br label %x9

x11:
	%x12 = load i32, i32* %x2
	%x13 = icmp sgt i32 %x12, 24
	br i1 %x13, label %x10, label %x9

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = alloca i32
	store i32 10, i32* %x1
	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	%x4 = alloca i32
	store i32 10, i32* %x4


	%x5 = load i32, i32* %x0
	call void @putint(i32 %x5)

	%x6 = alloca i32
	store i32 20, i32* %x6
	%x7 = load i32, i32* %x6
	call void @putint(i32 %x7)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)

	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)
	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)
	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)
	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	%x1 = load i32, i32* %x0
	call void @putint(i32 %x1)
	%x2 = alloca i32
	store i32 20, i32* %x2
	%x3 = load i32, i32* %x2
	call void @putint(i32 %x3)
	ret i32 0

}declare void @putch(i32)
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 1, i32* %x0
	%x1 = alloca i32
	store i32 0, i32* %x1
	%x2 = alloca i32
	store i32 2, i32* %x2
	%x3 = load i32, i32* %x1
	%x4 = load i32, i32* %x2
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* %x1
	%x6 = alloca i32
	store i32 3, i32* %x6
	%x7 = load i32, i32* %x1
	%x8 = load i32, i32* %x6
	%x9 = add i32 %x7, %x8
	store i32 %x9, i32* %x1
	%x10 = load i32, i32* %x1
	call void @putint(i32 %x10)
	%x11 = load i32, i32* %x6
	call void @putint(i32 %x11)
	call void @putch(i32 10)
	store i32 4, i32* %x6
	%x12 = load i32, i32* %x1
	%x13 = alloca i32
	store i32 %x12, i32* %x13
	%x14 = load i32, i32* %x13
	%x15 = load i32, i32* %x6
	%x16 = add i32 %x14, %x15
	%x17 = alloca i32
	store i32 %x16, i32* %x17
	%x18 = alloca i32
	store i32 5, i32* %x18
	%x19 = load i32, i32* %x17
	%x20 = alloca i32
	store i32 %x19, i32* %x20
	%x21 = load i32, i32* %x20
	%x22 = load i32, i32* %x18
	%x23 = add i32 %x21, %x22
	%x24 = alloca i32
	store i32 %x23, i32* %x24
	store i32 6, i32* %x18
	%x25 = load i32, i32* %x24
	call void @putint(i32 %x25)
	%x26 = load i32, i32* %x18
	call void @putint(i32 %x26)
	call void @putch(i32 10)
	%x27 = load i32, i32* %x24
	%x28 = load i32, i32* %x18
	%x29 = add i32 %x27, %x28
	store i32 %x29, i32* %x24
	store i32 7, i32* %x18
	%x30 = load i32, i32* %x24
	%x31 = load i32, i32* %x18
	%x32 = add i32 %x30, %x31
	store i32 %x32, i32* %x24
	%x33 = load i32, i32* %x24
	call void @putint(i32 %x33)
	%x34 = load i32, i32* %x18
	call void @putint(i32 %x34)
	%x35 = load i32, i32* %x24
	%x36 = load i32, i32* %x18
	%x37 = add i32 %x35, %x36
	store i32 %x37, i32* %x24
	call void @putch(i32 10)
	%x38 = load i32, i32* %x1
	call void @putint(i32 %x38)
	%x39 = load i32, i32* %x2
	call void @putint(i32 %x39)
	ret i32 0

}declare void @putch(i32)
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 1, i32* %x0
	%x1 = alloca i32
	store i32 0, i32* %x1
	%x2 = alloca i32
	store i32 2, i32* %x2
	%x3 = load i32, i32* %x1
	%x4 = load i32, i32* %x2
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* %x1
	%x6 = alloca i32
	store i32 3, i32* %x6
	%x7 = load i32, i32* %x1
	%x8 = load i32, i32* %x6
	%x9 = add i32 %x7, %x8
	store i32 %x9, i32* %x1
	%x10 = load i32, i32* %x1
	call void @putint(i32 %x10)
	%x11 = load i32, i32* %x6
	call void @putint(i32 %x11)
	call void @putch(i32 10)
	store i32 4, i32* %x6
	%x12 = load i32, i32* %x1
	%x13 = alloca i32
	store i32 %x12, i32* %x13
	%x14 = load i32, i32* %x13
	%x15 = load i32, i32* %x6
	%x16 = add i32 %x14, %x15
	%x17 = alloca i32
	store i32 %x16, i32* %x17
	%x18 = alloca i32
	store i32 5, i32* %x18
	%x19 = load i32, i32* %x17
	%x20 = alloca i32
	store i32 %x19, i32* %x20
	%x21 = load i32, i32* %x20
	%x22 = load i32, i32* %x18
	%x23 = add i32 %x21, %x22
	%x24 = alloca i32
	store i32 %x23, i32* %x24
	store i32 6, i32* %x18
	%x25 = load i32, i32* %x24
	call void @putint(i32 %x25)
	%x26 = load i32, i32* %x18
	call void @putint(i32 %x26)
	call void @putch(i32 10)
	%x27 = load i32, i32* %x24
	%x28 = load i32, i32* %x18
	%x29 = add i32 %x27, %x28
	store i32 %x29, i32* %x24
	store i32 7, i32* %x18
	%x30 = load i32, i32* %x24
	%x31 = load i32, i32* %x18
	%x32 = add i32 %x30, %x31
	store i32 %x32, i32* %x24
	%x33 = load i32, i32* %x24
	call void @putint(i32 %x33)
	%x34 = load i32, i32* %x18
	call void @putint(i32 %x34)
	%x35 = load i32, i32* %x24
	%x36 = load i32, i32* %x18
	%x37 = add i32 %x35, %x36
	store i32 %x37, i32* %x24
	call void @putch(i32 10)
	%x38 = load i32, i32* %x1
	call void @putint(i32 %x38)
	%x39 = load i32, i32* %x2
	call void @putint(i32 %x39)
	ret i32 0

}declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 3389, i32* %x0
	br label %x3

x1:
	%x33 = load i32, i32* %x9
	call void @putint(i32 %x33)
	ret i32 0

x2:
	%x7 = load i32, i32* %x0
	%x8 = add i32 %x7, 1
	store i32 %x8, i32* %x0
	%x9 = alloca i32
	store i32 112, i32* %x9
	br label %x11
	br label %x1

x3:
	%x4 = load i32, i32* %x0
	%x5 = icmp slt i32 %x4, 10000
	br i1 %x5, label %x2, label %x1

x10:
	%x15 = load i32, i32* %x9
	%x16 = sub i32 %x15, 88
	store i32 %x16, i32* %x9
	br label %x18
	br label %x1

x11:
	%x12 = load i32, i32* %x9
	%x13 = icmp sgt i32 %x12, 10
	br i1 %x13, label %x10, label %x1

x17:
	%x22 = alloca i32
	store i32 9, i32* %x22
	%x23 = alloca i32
	store i32 11, i32* %x23
	store i32 10, i32* %x22
	%x24 = load i32, i32* %x9
	%x25 = load i32, i32* %x22
	%x26 = sub i32 %x24, %x25
	store i32 %x26, i32* %x9
	%x27 = alloca i32
	store i32 11, i32* %x27
	%x28 = load i32, i32* %x9
	%x29 = load i32, i32* %x27
	%x30 = add i32 %x28, %x29
	%x31 = load i32, i32* %x23
	%x32 = add i32 %x30, %x31
	store i32 %x32, i32* %x9
	br label %x1

x18:
	%x19 = load i32, i32* %x9
	%x20 = icmp slt i32 %x19, 1000
	br i1 %x20, label %x17, label %x1

}