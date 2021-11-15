
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	%x1 = alloca i32
	store i32 56, i32* %x0
	store i32 4, i32* %x1
	%x2 = load i32, i32* %x0
	%x3 = sub i32 0, 4
	%x4 = sub i32 %x2, %x3
	%x5 = load i32, i32* %x1
	%x6 = add i32 %x4, %x5
	store i32 %x6, i32* %x0
	br label %x9

x7:
	%x26 = load i32, i32* %x0
	call void @putint(i32 %x26)
	ret i32 0

x8:
	%x21 = sub i32 0, 1
	%x22 = sub i32 0, %x21
	%x23 = sub i32 0, %x22
	store i32 %x23, i32* %x0
	br label %x7

x9:
	%x11 = load i32, i32* %x0
	%x12 = icmp eq i32 %x11, 0
	%x13 = zext i1 %x12 to i32
	%x14 = icmp eq i32 %x13, 0
	%x15 = zext i1 %x14 to i32
	%x16 = icmp eq i32 %x15, 0
	%x17 = zext i1 %x16 to i32
	%x18 = sub i32 0, %x17
	%x19 = icmp ne i32 %x18, 0
	br i1 %x19, label %x8, label %x10

x10:
	%x24 = load i32, i32* %x1
	%x25 = add i32 0, %x24
	store i32 %x25, i32* %x0
	br label %x7

}
