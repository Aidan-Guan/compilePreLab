declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
declare i32 @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	%x36 = call i32 @putint(i32 %x35)
	%x37 = add i32 0, 0
	ret i32 %x37
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 2
	%x2 = add i32 0, 2
	%x3 = alloca [2 x [2 x i32]]
	%x4 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 0, i32 0
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	%x6 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 0
	%x7 = add i32 0, 2
	store i32 %x7, i32* %x6
	%x8 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 1, i32 1
	%x9 = add i32 0, 3
	store i32 %x9, i32* %x8
	%x10 = add i32 0, 2
	%x11 = add i32 0, 2
	%x12 = alloca [2 x [2 x i32]]
	%x13 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 0
	%x14 = add i32 0, 0
	%x15 = add i32 0, 0
	%x16 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x14, i32 %x15
	%x17 = load i32, i32* %x16
	store i32 %x17, i32* %x13
	%x18 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 0, i32 1
	%x19 = add i32 0, 1
	%x20 = add i32 0, 1
	%x21 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x19, i32 %x20
	%x22 = load i32, i32* %x21
	store i32 %x22, i32* %x18
	%x23 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 0
	%x24 = add i32 0, 5
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 1, i32 1
	%x26 = add i32 0, 6
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 1
	%x28 = add i32 0, 1
	%x29 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x12, i32 0, i32 %x27, i32 %x28
	%x30 = load i32, i32* %x29
	%x31 = add i32 0, 1
	%x32 = add i32 0, 0
	%x33 = getelementptr  [2 x [2 x i32]],  [2 x [2 x i32]]* %x3, i32 0, i32 %x31, i32 %x32
	%x34 = load i32, i32* %x33
	%x35 = add i32 %x30, %x34
	call void @putint(i32 %x35)
	%x36 = add i32 0, 0
	ret i32 %x36
}declare void @putint(i32)
@c = dso_local constant [2 x [1 x i32]] [ [1 x i32][ i32 1], [1 x i32][ i32 3]]
@b = dso_local global [2 x [3 x i32]] [ [3 x i32][ i32 1, i32 0, i32 0], [3 x i32] zeroinitializer]
@e = dso_local global [4 x [4 x i32]] zeroinitializer
@d = dso_local global [5 x i32] zeroinitializer
@a = dso_local global [3 x i32] [ i32 1, i32 2, i32 0]
define dso_local i32 @main(){
	%x6 = add i32 0, 1
	%x7 = add i32 0, 0
	%x8 = getelementptr  [2 x [1 x i32]],  [2 x [1 x i32]]* @c, i32 0, i32 %x6, i32 %x7
	%x9 = load i32, i32* %x8
	%x10 = add i32 0, 0
	%x11 = add i32 0, 0
	%x12 = getelementptr  [2 x [3 x i32]],  [2 x [3 x i32]]* @b, i32 0, i32 %x10, i32 %x11
	%x13 = load i32, i32* %x12
	%x14 = add i32 %x9, %x13
	%x15 = add i32 0, 0
	%x16 = add i32 0, 0
	%x17 = getelementptr  [2 x [1 x i32]],  [2 x [1 x i32]]* @c, i32 0, i32 %x15, i32 %x16
	%x18 = load i32, i32* %x17
	%x19 = add i32 %x14, %x18
	%x20 = add i32 0, 1
	%x21 = getelementptr  [3 x i32],  [3 x i32]* @a, i32 0, i32 %x20
	%x22 = load i32, i32* %x21
	%x23 = add i32 %x19, %x22
	%x24 = add i32 0, 4
	%x25 = getelementptr  [5 x i32],  [5 x i32]* @d, i32 0, i32 %x24
	%x26 = load i32, i32* %x25
	%x27 = add i32 %x23, %x26
	call void @putint(i32 %x27)
	%x28 = add i32 0, 0
	ret i32 %x28
}declare void @putint(i32)
@a = dso_local global i32 1
@b = dso_local global [2 x i32] [ i32 1, i32 1]
define dso_local i32 @main(){
	%x4 = add i32 0, 1
	%x5 = getelementptr  [2 x i32],  [2 x i32]* @b, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}