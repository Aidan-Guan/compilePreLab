
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	%x1 = alloca i32
	%x2 = alloca i32
	store i32 1, i32* %x2
	%x3 = alloca i32
	%x4 = alloca i32
	store i32 5, i32* %x0
	store i32 5, i32* %x1
	%x5 = sub i32 0, 2
	store i32 %x5, i32* %x3
	store i32 2, i32* %x4
	br label %x8

x6:
	%x37 = load i32, i32* %x4
	ret i32 0

x7:
	%x19 = load i32, i32* %x4
	%x20 = add i32 %x19, 1
	store i32 %x20, i32* %x4
	br label %x6

x8:
	%x10 = load i32, i32* %x0
	%x11 = load i32, i32* %x1
	%x12 = add i32 %x10, %x11
	%x13 = load i32, i32* %x2
	%x14 = add i32 %x12, %x13
	%x15 = load i32, i32* %x3
	%x16 = add i32 %x14, %x15
	%x17 = icmp eq i32 %x16, 10
	br i1 %x17, label %x7, label %x9

x9:
	br label %x22
	br label %x6

x21:
	%x33 = load i32, i32* %x4
	%x34 = add i32 %x33, 2
	store i32 %x34, i32* %x4
	br label %x6

x22:
	%x24 = load i32, i32* %x0
	%x25 = load i32, i32* %x1
	%x26 = add i32 %x24, %x25
	%x27 = load i32, i32* %x2
	%x28 = add i32 %x26, %x27
	%x29 = load i32, i32* %x3
	%x30 = add i32 %x28, %x29
	%x31 = icmp eq i32 %x30, 8
	br i1 %x31, label %x21, label %x23

x23:
	%x35 = load i32, i32* %x4
	%x36 = add i32 %x35, 4
	store i32 %x36, i32* %x4
	br label %x6

}

