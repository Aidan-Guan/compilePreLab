declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	%x1 = alloca i32
	%x2 = alloca i32
	%x3 = alloca i32
	%x4 = alloca i32
	store i32 5, i32* %x0
	store i32 5, i32* %x1
	store i32 1, i32* %x2
	%x5 = sub i32 0, 2
	store i32 %x5, i32* %x3
	%x6 = load i32, i32* %x3
	%x7 = mul i32 %x6, 1
	%x8 = sdiv i32 %x7, 2
	%x9 = load i32, i32* %x0
	%x10 = load i32, i32* %x1
	%x11 = sub i32 %x9, %x10
	%x12 = add i32 %x8, %x11
	%x13 = load i32, i32* %x2
	%x14 = add i32 %x13, 3
	%x15 = sub i32 0, %x14
	%x16 = srem i32 %x15, 2
	%x17 = sub i32 %x12, %x16
	store i32 %x17, i32* %x4
	%x18 = load i32, i32* %x4
	call void @putint(i32 %x18)
	%x19 = load i32, i32* %x3
	%x20 = srem i32 %x19, 2
	%x21 = add i32 %x20, 67
	%x22 = load i32, i32* %x0
	%x23 = load i32, i32* %x1
	%x24 = sub i32 %x22, %x23
	%x25 = sub i32 0, %x24
	%x26 = add i32 %x21, %x25
	%x27 = load i32, i32* %x2
	%x28 = add i32 %x27, 2
	%x29 = srem i32 %x28, 2
	%x30 = sub i32 0, %x29
	%x31 = sub i32 %x26, %x30
	store i32 %x31, i32* %x4
	%x32 = load i32, i32* %x4
	%x33 = add i32 %x32, 3
	store i32 %x33, i32* %x4
	%x34 = load i32, i32* %x4
	call void @putint(i32 %x34)
	ret i32 0
}