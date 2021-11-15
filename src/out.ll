
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 5, i32* %x0
	%x1 = alloca i32
	store i32 10, i32* %x1
	br label %x4

x2:
	%x16 = load i32, i32* %x0
	call void @putint(i32 %x16)
	ret i32 0

x3:
	br label %x9
	br label %x2

x4:
	%x5 = load i32, i32* %x0
	%x6 = icmp eq i32 %x5, 5
	br i1 %x6, label %x3, label %x2

x8:
	store i32 25, i32* %x0
	br label %x2

x9:
	%x11 = load i32, i32* %x1
	%x12 = icmp eq i32 %x11, 10
	br i1 %x12, label %x8, label %x10

x10:
	%x14 = load i32, i32* %x0
	%x15 = add i32 %x14, 15
	store i32 %x15, i32* %x0
	br label %x2

}

