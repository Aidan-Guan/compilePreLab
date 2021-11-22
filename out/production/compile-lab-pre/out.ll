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

}