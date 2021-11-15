
define dso_local i32 @main(){
	%x0 = alloca i32
	br label %x3

x1:
	ret i32 0

x2:
	br label %x9
	br label %x1

x3:
	%x5 = load i32, i32* %x0
	%x6 = icmp eq i32 %x5, 5
	br i1 %x6, label %x2, label %x4

x4:
	%x13 = load i32, i32* %x0
	%x14 = add i32 %x13, 15
	store i32 %x14, i32* %x0
	br label %x1

x8:
	store i32 25, i32* %x0
	br label %x1

x9:
	%x10 = load i32, i32* %x0
	%x11 = icmp eq i32 %x10, 10
	br i1 %x11, label %x8, label %x1

}