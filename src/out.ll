
define dso_local i32 @main(){
	%x0 = alloca i32
	store i32 9, i32* %x0
	br label %x3

x1:
	%x12 = add i32 93, 6
	store i32 %x12, i32* %x0
	ret i32 0

x2:
	store i32 2, i32* %x0
	br label %x1

x3:
	%x5 = load i32, i32* %x0
	%x6 = icmp sle i32 %x5, 1
	br i1 %x6,label %x2, label %x4

x4:
	%x9 = load i32, i32* %x0
	%x10 = icmp sge i32 %x9, 2
	br i1 %x10,label %x2, label %x8

x8:
	store i32 3, i32* %x0
	br label %x1

}

