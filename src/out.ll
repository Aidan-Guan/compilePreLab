
declare void @putint(i32)
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 10, i32* %x0
	br label %x3

x1:
	%x18 = load i32, i32* %x0
	call void @putint(i32 %x18)
	ret i32 0

x2:
	%x15 = sub i32 0, 1
	%x16 = sub i32 0, %x15
	%x17 = sub i32 0, %x16
	store i32 %x17, i32* %x0
	br label %x1

x3:
	%x5 = load i32, i32* %x0
	%x6 = icmp eq i32 %x5, 0
	%x7 = zext i1 %x6 to i32
	%x8 = icmp eq i32 %x7, 0
	%x9 = zext i1 %x8 to i32
	%x10 = icmp eq i32 %x9, 0
	%x11 = zext i1 %x10 to i32
	%x12 = sub i32 0, %x11
	%x13 = icmp ne i32 %x12, 0
	br i1 %x13, label %x2, label %x4

x4:
	store i32 0, i32* %x0
	br label %x1

}
