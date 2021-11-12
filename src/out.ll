define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 8, i32* %x0
	%x1 = alloca i32
	store i32 456, i32* %x1
	%x2 = alloca i32
	store i32 8456, i32* %x2
	%x3 = load i32, i32* %x2
	%x4 = load i32, i32* %x1
	%x5 = sub i32 %x3, %x4
	%x6 = sdiv i32 %x5, 1000
	%x7 = load i32, i32* %x0
	%x8 = sub i32 %x6, %x7
	%x9 = alloca i32
	store i32 %x8, i32* %x9
	%x10 = alloca i32
	store i32 2, i32* %x10
	%x11 = load i32, i32* %x9
	%x12 = load i32, i32* %x10
	%x13 = add i32 %x11, %x12
	store i32 %x13, i32* %x9
	%x14 = load i32, i32* %x9
	%x15 = load i32, i32* %x10
	%x16 = sub i32 %x14, %x15
	%x17 = add i32 %x16, 0
	ret i32 %x17
}
