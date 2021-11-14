
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 1, i32* %x0
	br label %x3

x1:
	br label %x9

x2:
	store i32 2, i32* %x0
	br label %x1

x3:
	%x4 = load i32, i32* %x0
	%x5 = icmp slt i32 %x4, 2
	br i1 %x5, label %x2, label %x1

x7:
	ret i32 2

x8:
	store i32 2, i32* %x0
	br label %x7

x9:
	%x10 = load i32, i32* %x0
	%x11 = icmp sgt i32 %x10, 2
	br i1 %x11, label %x8, label %x7

}