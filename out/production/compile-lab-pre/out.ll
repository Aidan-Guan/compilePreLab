declare void @putint(i32)
declare i32 @getch()
@ints = dso_local global [10000 x i32] zeroinitializer
@intt = dso_local global i32 0
@chas = dso_local global [10000 x i32] zeroinitializer
@chat = dso_local global i32 0
@i = dso_local global i32 0
@ii = dso_local global i32 1
@c = dso_local global i32 0
@get = dso_local global [10000 x i32] zeroinitializer
@get2 = dso_local global [10000 x i32] zeroinitializer
define dso_local i32 @isdigit(i32 %x0){
	%x2 = alloca i32
	store i32 %x0, i32* %x2
	%x3 = load i32, i32* %x2
	%x4 = add i32 0, 48
	%x5 = icmp sge i32 %x3, %x4
	br i1 %x5, label %block2, label %block1

block2:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 57
	%x8 = icmp sle i32 %x6, %x7
	br i1 %x8, label %block0, label %block1

block0:
	%x9 = add i32 0, 1
	ret i32 %x9

block1:
	%x10 = add i32 0, 0
	ret i32 %x10
}
define dso_local i32 @power(i32 %x0, i32 %x1){
	%x3 = alloca i32
	store i32 %x1, i32* %x3
	%x4 = alloca i32
	store i32 %x0, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 1
	store i32 %x6, i32* %x5
	br label %block0

block0:
	%x7 = load i32, i32* %x3
	%x8 = add i32 0, 0
	%x9 = icmp ne i32 %x7, %x8
	br i1 %x9, label %block1, label %block2

block1:
	%x10 = load i32, i32* %x5
	%x11 = load i32, i32* %x4
	%x12 = mul i32 %x10, %x11
	store i32 %x12, i32* %x5
	%x13 = load i32, i32* %x3
	%x14 = add i32 0, 1
	%x15 = sub i32 %x13, %x14
	store i32 %x15, i32* %x3
	br label %block0

block2:
	%x16 = load i32, i32* %x5
	ret i32 %x16
}
define dso_local i32 @getstr(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = alloca i32
	%x4 = call i32 @getch()
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	br label %block0

block0:
	%x7 = load i32, i32* %x3
	%x8 = add i32 0, 13
	%x9 = icmp ne i32 %x7, %x8
	br i1 %x9, label %block3, label %block2

block3:
	%x10 = load i32, i32* %x3
	%x11 = add i32 0, 10
	%x12 = icmp ne i32 %x10, %x11
	br i1 %x12, label %block1, label %block2

block1:
	%x13 = load i32, i32* %x5
	%x14 = load i32*, i32* * %x2
	%x15 = getelementptr i32, i32* %x14, i32 %x13
	%x16 = load i32, i32* %x3
	store i32 %x16, i32* %x15
	%x17 = load i32, i32* %x5
	%x18 = add i32 0, 1
	%x19 = add i32 %x17, %x18
	store i32 %x19, i32* %x5
	%x20 = call i32 @getch()
	store i32 %x20, i32* %x3
	br label %block0

block2:
	%x21 = load i32, i32* %x5
	ret i32 %x21
}
define dso_local void @intpush(i32 %x0){
	%x2 = alloca i32
	store i32 %x0, i32* %x2
	%x3 = load i32, i32* @intt
	%x4 = add i32 0, 1
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* @intt
	%x6 = load i32, i32* @intt
	%x7 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x6
	%x8 = load i32, i32* %x2
	store i32 %x8, i32* %x7
	ret void
}
define dso_local void @chapush(i32 %x0){
	%x2 = alloca i32
	store i32 %x0, i32* %x2
	%x3 = load i32, i32* @chat
	%x4 = add i32 0, 1
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* @chat
	%x6 = load i32, i32* @chat
	%x7 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x6
	%x8 = load i32, i32* %x2
	store i32 %x8, i32* %x7
	ret void
}
define dso_local i32 @intpop(){
	%x1 = load i32, i32* @intt
	%x2 = add i32 0, 1
	%x3 = sub i32 %x1, %x2
	store i32 %x3, i32* @intt
	%x4 = load i32, i32* @intt
	%x5 = add i32 0, 1
	%x6 = add i32 %x4, %x5
	%x7 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x6
	%x8 = load i32, i32* %x7
	ret i32 %x8
}
define dso_local i32 @chapop(){
	%x1 = load i32, i32* @chat
	%x2 = add i32 0, 1
	%x3 = sub i32 %x1, %x2
	store i32 %x3, i32* @chat
	%x4 = load i32, i32* @chat
	%x5 = add i32 0, 1
	%x6 = add i32 %x4, %x5
	%x7 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x6
	%x8 = load i32, i32* %x7
	ret i32 %x8
}
define dso_local void @intadd(i32 %x0){
	%x2 = alloca i32
	store i32 %x0, i32* %x2
	%x3 = load i32, i32* @intt
	%x4 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x3
	%x5 = load i32, i32* @intt
	%x6 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x5
	%x7 = load i32, i32* %x6
	%x8 = add i32 0, 10
	%x9 = mul i32 %x7, %x8
	store i32 %x9, i32* %x4
	%x10 = load i32, i32* @intt
	%x11 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x10
	%x12 = load i32, i32* @intt
	%x13 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x12
	%x14 = load i32, i32* %x13
	%x15 = load i32, i32* %x2
	%x16 = add i32 %x14, %x15
	store i32 %x16, i32* %x11
	ret void
}
define dso_local i32 @find(){
	%x1 = call i32 @chapop()
	store i32 %x1, i32* @c
	%x2 = load i32, i32* @ii
	%x3 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x2
	%x4 = add i32 0, 32
	store i32 %x4, i32* %x3
	%x5 = load i32, i32* @ii
	%x6 = add i32 0, 1
	%x7 = add i32 %x5, %x6
	%x8 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x7
	%x9 = load i32, i32* @c
	store i32 %x9, i32* %x8
	%x10 = load i32, i32* @ii
	%x11 = add i32 0, 2
	%x12 = add i32 %x10, %x11
	store i32 %x12, i32* @ii
	%x13 = load i32, i32* @chat
	%x14 = add i32 0, 0
	%x15 = icmp eq i32 %x13, %x14
	br i1 %x15, label %block0, label %block1

block0:
	%x16 = add i32 0, 0
	ret i32 %x16

block1:
	%x17 = add i32 0, 1
	ret i32 %x17
}
define dso_local i32 @main(){
	%x1 = add i32 0, 0
	store i32 %x1, i32* @intt
	%x2 = add i32 0, 0
	store i32 %x2, i32* @chat
	%x3 = alloca i32
	%x4 = getelementptr  [10000 x i32],  [10000 x i32]*@get, i32 0, i32 0
	%x5 = call i32 @getstr(i32* %x4)
	store i32 %x5, i32* %x3
	br label %block0

block0:
	%x6 = load i32, i32* @i
	%x7 = load i32, i32* %x3
	%x8 = icmp slt i32 %x6, %x7
	br i1 %x8, label %block1, label %block2

block1:
	%x9 = load i32, i32* @i
	%x10 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x9
	%x11 = load i32, i32* %x10
	%x12 = call i32 @isdigit(i32 %x11)
	%x13 = add i32 0, 1
	%x14 = icmp eq i32 %x12, %x13
	br i1 %x14, label %block3, label %block4

block3:
	%x15 = load i32, i32* @ii
	%x16 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x15
	%x17 = load i32, i32* @i
	%x18 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x17
	%x19 = load i32, i32* %x18
	store i32 %x19, i32* %x16
	%x20 = load i32, i32* @ii
	%x21 = add i32 0, 1
	%x22 = add i32 %x20, %x21
	store i32 %x22, i32* @ii
	br label %block5

block4:
	%x23 = load i32, i32* @i
	%x24 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x23
	%x25 = load i32, i32* %x24
	%x26 = add i32 0, 40
	%x27 = icmp eq i32 %x25, %x26
	br i1 %x27, label %block6, label %block7

block6:
	%x28 = add i32 0, 40
	call void @chapush(i32 %x28)
	br label %block7

block7:
	%x29 = load i32, i32* @i
	%x30 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x29
	%x31 = load i32, i32* %x30
	%x32 = add i32 0, 94
	%x33 = icmp eq i32 %x31, %x32
	br i1 %x33, label %block8, label %block9

block8:
	%x34 = add i32 0, 94
	call void @chapush(i32 %x34)
	br label %block9

block9:
	%x35 = load i32, i32* @i
	%x36 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x35
	%x37 = load i32, i32* %x36
	%x38 = add i32 0, 41
	%x39 = icmp eq i32 %x37, %x38
	br i1 %x39, label %block10, label %block11

block10:
	%x40 = call i32 @chapop()
	store i32 %x40, i32* @c
	br label %block12

block12:
	%x41 = load i32, i32* @c
	%x42 = add i32 0, 40
	%x43 = icmp ne i32 %x41, %x42
	br i1 %x43, label %block13, label %block14

block13:
	%x44 = load i32, i32* @ii
	%x45 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x44
	%x46 = add i32 0, 32
	store i32 %x46, i32* %x45
	%x47 = load i32, i32* @ii
	%x48 = add i32 0, 1
	%x49 = add i32 %x47, %x48
	%x50 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x49
	%x51 = load i32, i32* @c
	store i32 %x51, i32* %x50
	%x52 = load i32, i32* @ii
	%x53 = add i32 0, 2
	%x54 = add i32 %x52, %x53
	store i32 %x54, i32* @ii
	%x55 = call i32 @chapop()
	store i32 %x55, i32* @c
	br label %block12

block14:
	br label %block11

block11:
	%x56 = load i32, i32* @i
	%x57 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x56
	%x58 = load i32, i32* %x57
	%x59 = add i32 0, 43
	%x60 = icmp eq i32 %x58, %x59
	br i1 %x60, label %block15, label %block16

block15:
	br label %block17

block17:
	%x61 = load i32, i32* @chat
	%x62 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x61
	%x63 = load i32, i32* %x62
	%x64 = add i32 0, 43
	%x65 = icmp eq i32 %x63, %x64
	br i1 %x65, label %block18, label %block20

block20:
	%x66 = load i32, i32* @chat
	%x67 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x66
	%x68 = load i32, i32* %x67
	%x69 = add i32 0, 45
	%x70 = icmp eq i32 %x68, %x69
	br i1 %x70, label %block18, label %block21

block21:
	%x71 = load i32, i32* @chat
	%x72 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x71
	%x73 = load i32, i32* %x72
	%x74 = add i32 0, 42
	%x75 = icmp eq i32 %x73, %x74
	br i1 %x75, label %block18, label %block22

block22:
	%x76 = load i32, i32* @chat
	%x77 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x76
	%x78 = load i32, i32* %x77
	%x79 = add i32 0, 47
	%x80 = icmp eq i32 %x78, %x79
	br i1 %x80, label %block18, label %block23

block23:
	%x81 = load i32, i32* @chat
	%x82 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x81
	%x83 = load i32, i32* %x82
	%x84 = add i32 0, 37
	%x85 = icmp eq i32 %x83, %x84
	br i1 %x85, label %block18, label %block24

block24:
	%x86 = load i32, i32* @chat
	%x87 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x86
	%x88 = load i32, i32* %x87
	%x89 = add i32 0, 94
	%x90 = icmp eq i32 %x88, %x89
	br i1 %x90, label %block18, label %block19

block18:
	%x91 = call i32 @find()
	%x92 = add i32 0, 0
	%x93 = icmp eq i32 %x91, %x92
	br i1 %x93, label %block25, label %block26

block25:
	br label %block19

block26:
	br label %block17

block19:
	%x94 = add i32 0, 43
	call void @chapush(i32 %x94)
	br label %block16

block16:
	%x95 = load i32, i32* @i
	%x96 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x95
	%x97 = load i32, i32* %x96
	%x98 = add i32 0, 45
	%x99 = icmp eq i32 %x97, %x98
	br i1 %x99, label %block27, label %block28

block27:
	br label %block29

block29:
	%x100 = load i32, i32* @chat
	%x101 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x100
	%x102 = load i32, i32* %x101
	%x103 = add i32 0, 43
	%x104 = icmp eq i32 %x102, %x103
	br i1 %x104, label %block30, label %block32

block32:
	%x105 = load i32, i32* @chat
	%x106 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x105
	%x107 = load i32, i32* %x106
	%x108 = add i32 0, 45
	%x109 = icmp eq i32 %x107, %x108
	br i1 %x109, label %block30, label %block33

block33:
	%x110 = load i32, i32* @chat
	%x111 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x110
	%x112 = load i32, i32* %x111
	%x113 = add i32 0, 42
	%x114 = icmp eq i32 %x112, %x113
	br i1 %x114, label %block30, label %block34

block34:
	%x115 = load i32, i32* @chat
	%x116 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x115
	%x117 = load i32, i32* %x116
	%x118 = add i32 0, 47
	%x119 = icmp eq i32 %x117, %x118
	br i1 %x119, label %block30, label %block35

block35:
	%x120 = load i32, i32* @chat
	%x121 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x120
	%x122 = load i32, i32* %x121
	%x123 = add i32 0, 37
	%x124 = icmp eq i32 %x122, %x123
	br i1 %x124, label %block30, label %block36

block36:
	%x125 = load i32, i32* @chat
	%x126 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x125
	%x127 = load i32, i32* %x126
	%x128 = add i32 0, 94
	%x129 = icmp eq i32 %x127, %x128
	br i1 %x129, label %block30, label %block31

block30:
	%x130 = call i32 @find()
	%x131 = add i32 0, 0
	%x132 = icmp eq i32 %x130, %x131
	br i1 %x132, label %block37, label %block38

block37:
	br label %block31

block38:
	br label %block29

block31:
	%x133 = add i32 0, 45
	call void @chapush(i32 %x133)
	br label %block28

block28:
	%x134 = load i32, i32* @i
	%x135 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x134
	%x136 = load i32, i32* %x135
	%x137 = add i32 0, 42
	%x138 = icmp eq i32 %x136, %x137
	br i1 %x138, label %block39, label %block40

block39:
	br label %block41

block41:
	%x139 = load i32, i32* @chat
	%x140 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x139
	%x141 = load i32, i32* %x140
	%x142 = add i32 0, 42
	%x143 = icmp eq i32 %x141, %x142
	br i1 %x143, label %block42, label %block44

block44:
	%x144 = load i32, i32* @chat
	%x145 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x144
	%x146 = load i32, i32* %x145
	%x147 = add i32 0, 47
	%x148 = icmp eq i32 %x146, %x147
	br i1 %x148, label %block42, label %block45

block45:
	%x149 = load i32, i32* @chat
	%x150 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x149
	%x151 = load i32, i32* %x150
	%x152 = add i32 0, 37
	%x153 = icmp eq i32 %x151, %x152
	br i1 %x153, label %block42, label %block46

block46:
	%x154 = load i32, i32* @chat
	%x155 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x154
	%x156 = load i32, i32* %x155
	%x157 = add i32 0, 94
	%x158 = icmp eq i32 %x156, %x157
	br i1 %x158, label %block42, label %block43

block42:
	%x159 = call i32 @find()
	%x160 = add i32 0, 0
	%x161 = icmp eq i32 %x159, %x160
	br i1 %x161, label %block47, label %block48

block47:
	br label %block43

block48:
	br label %block41

block43:
	%x162 = add i32 0, 42
	call void @chapush(i32 %x162)
	br label %block40

block40:
	%x163 = load i32, i32* @i
	%x164 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x163
	%x165 = load i32, i32* %x164
	%x166 = add i32 0, 47
	%x167 = icmp eq i32 %x165, %x166
	br i1 %x167, label %block49, label %block50

block49:
	br label %block51

block51:
	%x168 = load i32, i32* @chat
	%x169 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x168
	%x170 = load i32, i32* %x169
	%x171 = add i32 0, 42
	%x172 = icmp eq i32 %x170, %x171
	br i1 %x172, label %block52, label %block54

block54:
	%x173 = load i32, i32* @chat
	%x174 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x173
	%x175 = load i32, i32* %x174
	%x176 = add i32 0, 47
	%x177 = icmp eq i32 %x175, %x176
	br i1 %x177, label %block52, label %block55

block55:
	%x178 = load i32, i32* @chat
	%x179 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x178
	%x180 = load i32, i32* %x179
	%x181 = add i32 0, 37
	%x182 = icmp eq i32 %x180, %x181
	br i1 %x182, label %block52, label %block56

block56:
	%x183 = load i32, i32* @chat
	%x184 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x183
	%x185 = load i32, i32* %x184
	%x186 = add i32 0, 94
	%x187 = icmp eq i32 %x185, %x186
	br i1 %x187, label %block52, label %block53

block52:
	%x188 = call i32 @find()
	%x189 = add i32 0, 0
	%x190 = icmp eq i32 %x188, %x189
	br i1 %x190, label %block57, label %block58

block57:
	br label %block53

block58:
	br label %block51

block53:
	%x191 = add i32 0, 47
	call void @chapush(i32 %x191)
	br label %block50

block50:
	%x192 = load i32, i32* @i
	%x193 = getelementptr  [10000 x i32],  [10000 x i32]* @get, i32 0, i32 %x192
	%x194 = load i32, i32* %x193
	%x195 = add i32 0, 37
	%x196 = icmp eq i32 %x194, %x195
	br i1 %x196, label %block59, label %block60

block59:
	br label %block61

block61:
	%x197 = load i32, i32* @chat
	%x198 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x197
	%x199 = load i32, i32* %x198
	%x200 = add i32 0, 42
	%x201 = icmp eq i32 %x199, %x200
	br i1 %x201, label %block62, label %block64

block64:
	%x202 = load i32, i32* @chat
	%x203 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x202
	%x204 = load i32, i32* %x203
	%x205 = add i32 0, 47
	%x206 = icmp eq i32 %x204, %x205
	br i1 %x206, label %block62, label %block65

block65:
	%x207 = load i32, i32* @chat
	%x208 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x207
	%x209 = load i32, i32* %x208
	%x210 = add i32 0, 37
	%x211 = icmp eq i32 %x209, %x210
	br i1 %x211, label %block62, label %block66

block66:
	%x212 = load i32, i32* @chat
	%x213 = getelementptr  [10000 x i32],  [10000 x i32]* @chas, i32 0, i32 %x212
	%x214 = load i32, i32* %x213
	%x215 = add i32 0, 94
	%x216 = icmp eq i32 %x214, %x215
	br i1 %x216, label %block62, label %block63

block62:
	%x217 = call i32 @find()
	%x218 = add i32 0, 0
	%x219 = icmp eq i32 %x217, %x218
	br i1 %x219, label %block67, label %block68

block67:
	br label %block63

block68:
	br label %block61

block63:
	%x220 = add i32 0, 37
	call void @chapush(i32 %x220)
	br label %block60

block60:
	%x221 = load i32, i32* @ii
	%x222 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x221
	%x223 = add i32 0, 32
	store i32 %x223, i32* %x222
	%x224 = load i32, i32* @ii
	%x225 = add i32 0, 1
	%x226 = add i32 %x224, %x225
	store i32 %x226, i32* @ii
	br label %block5

block5:
	%x227 = load i32, i32* @i
	%x228 = add i32 0, 1
	%x229 = add i32 %x227, %x228
	store i32 %x229, i32* @i
	br label %block0

block2:
	br label %block69

block69:
	%x230 = load i32, i32* @chat
	%x231 = add i32 0, 0
	%x232 = icmp sgt i32 %x230, %x231
	br i1 %x232, label %block70, label %block71

block70:
	%x233 = alloca i32
	%x234 = call i32 @chapop()
	store i32 %x234, i32* %x233
	%x235 = load i32, i32* @ii
	%x236 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x235
	%x237 = add i32 0, 32
	store i32 %x237, i32* %x236
	%x238 = load i32, i32* @ii
	%x239 = add i32 0, 1
	%x240 = add i32 %x238, %x239
	%x241 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x240
	%x242 = load i32, i32* %x233
	store i32 %x242, i32* %x241
	%x243 = load i32, i32* @ii
	%x244 = add i32 0, 2
	%x245 = add i32 %x243, %x244
	store i32 %x245, i32* @ii
	br label %block69

block71:
	%x246 = load i32, i32* @ii
	%x247 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x246
	%x248 = add i32 0, 64
	store i32 %x248, i32* %x247
	%x249 = add i32 0, 1
	store i32 %x249, i32* @i
	br label %block72

block72:
	%x250 = load i32, i32* @i
	%x251 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x250
	%x252 = load i32, i32* %x251
	%x253 = add i32 0, 64
	%x254 = icmp ne i32 %x252, %x253
	br i1 %x254, label %block73, label %block74

block73:
	%x255 = load i32, i32* @i
	%x256 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x255
	%x257 = load i32, i32* %x256
	%x258 = add i32 0, 43
	%x259 = icmp eq i32 %x257, %x258
	br i1 %x259, label %block75, label %block77

block77:
	%x260 = load i32, i32* @i
	%x261 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x260
	%x262 = load i32, i32* %x261
	%x263 = add i32 0, 45
	%x264 = icmp eq i32 %x262, %x263
	br i1 %x264, label %block75, label %block78

block78:
	%x265 = load i32, i32* @i
	%x266 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x265
	%x267 = load i32, i32* %x266
	%x268 = add i32 0, 42
	%x269 = icmp eq i32 %x267, %x268
	br i1 %x269, label %block75, label %block79

block79:
	%x270 = load i32, i32* @i
	%x271 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x270
	%x272 = load i32, i32* %x271
	%x273 = add i32 0, 47
	%x274 = icmp eq i32 %x272, %x273
	br i1 %x274, label %block75, label %block80

block80:
	%x275 = load i32, i32* @i
	%x276 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x275
	%x277 = load i32, i32* %x276
	%x278 = add i32 0, 37
	%x279 = icmp eq i32 %x277, %x278
	br i1 %x279, label %block75, label %block81

block81:
	%x280 = load i32, i32* @i
	%x281 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x280
	%x282 = load i32, i32* %x281
	%x283 = add i32 0, 94
	%x284 = icmp eq i32 %x282, %x283
	br i1 %x284, label %block75, label %block76

block75:
	%x285 = alloca i32
	%x286 = call i32 @intpop()
	store i32 %x286, i32* %x285
	%x287 = alloca i32
	%x288 = call i32 @intpop()
	store i32 %x288, i32* %x287
	%x289 = alloca i32
	%x290 = load i32, i32* @i
	%x291 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x290
	%x292 = load i32, i32* %x291
	%x293 = add i32 0, 43
	%x294 = icmp eq i32 %x292, %x293
	br i1 %x294, label %block83, label %block84

block83:
	%x295 = load i32, i32* %x285
	%x296 = load i32, i32* %x287
	%x297 = add i32 %x295, %x296
	store i32 %x297, i32* %x289
	br label %block84

block84:
	%x298 = load i32, i32* @i
	%x299 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x298
	%x300 = load i32, i32* %x299
	%x301 = add i32 0, 45
	%x302 = icmp eq i32 %x300, %x301
	br i1 %x302, label %block85, label %block86

block85:
	%x303 = load i32, i32* %x287
	%x304 = load i32, i32* %x285
	%x305 = sub i32 %x303, %x304
	store i32 %x305, i32* %x289
	br label %block86

block86:
	%x306 = load i32, i32* @i
	%x307 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x306
	%x308 = load i32, i32* %x307
	%x309 = add i32 0, 42
	%x310 = icmp eq i32 %x308, %x309
	br i1 %x310, label %block87, label %block88

block87:
	%x311 = load i32, i32* %x285
	%x312 = load i32, i32* %x287
	%x313 = mul i32 %x311, %x312
	store i32 %x313, i32* %x289
	br label %block88

block88:
	%x314 = load i32, i32* @i
	%x315 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x314
	%x316 = load i32, i32* %x315
	%x317 = add i32 0, 47
	%x318 = icmp eq i32 %x316, %x317
	br i1 %x318, label %block89, label %block90

block89:
	%x319 = load i32, i32* %x287
	%x320 = load i32, i32* %x285
	%x321 = sdiv i32 %x319, %x320
	store i32 %x321, i32* %x289
	br label %block90

block90:
	%x322 = load i32, i32* @i
	%x323 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x322
	%x324 = load i32, i32* %x323
	%x325 = add i32 0, 37
	%x326 = icmp eq i32 %x324, %x325
	br i1 %x326, label %block91, label %block92

block91:
	%x327 = load i32, i32* %x287
	%x328 = load i32, i32* %x285
	%x329 = srem i32 %x327, %x328
	store i32 %x329, i32* %x289
	br label %block92

block92:
	%x330 = load i32, i32* @i
	%x331 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x330
	%x332 = load i32, i32* %x331
	%x333 = add i32 0, 94
	%x334 = icmp eq i32 %x332, %x333
	br i1 %x334, label %block93, label %block94

block93:
	%x335 = load i32, i32* %x287
	%x336 = load i32, i32* %x285
	%x337 = call i32 @power(i32 %x335, i32 %x336)
	store i32 %x337, i32* %x289
	br label %block94

block94:
	%x338 = load i32, i32* %x289
	call void @intpush(i32 %x338)
	br label %block82

block76:
	%x339 = load i32, i32* @i
	%x340 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x339
	%x341 = load i32, i32* %x340
	%x342 = add i32 0, 32
	%x343 = icmp ne i32 %x341, %x342
	br i1 %x343, label %block95, label %block96

block95:
	%x344 = load i32, i32* @i
	%x345 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x344
	%x346 = load i32, i32* %x345
	%x347 = add i32 0, 48
	%x348 = sub i32 %x346, %x347
	call void @intpush(i32 %x348)
	%x349 = add i32 0, 1
	store i32 %x349, i32* @ii
	br label %block97

block97:
	%x350 = load i32, i32* @i
	%x351 = load i32, i32* @ii
	%x352 = add i32 %x350, %x351
	%x353 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x352
	%x354 = load i32, i32* %x353
	%x355 = add i32 0, 32
	%x356 = icmp ne i32 %x354, %x355
	br i1 %x356, label %block98, label %block99

block98:
	%x357 = load i32, i32* @i
	%x358 = load i32, i32* @ii
	%x359 = add i32 %x357, %x358
	%x360 = getelementptr  [10000 x i32],  [10000 x i32]* @get2, i32 0, i32 %x359
	%x361 = load i32, i32* %x360
	%x362 = add i32 0, 48
	%x363 = sub i32 %x361, %x362
	call void @intadd(i32 %x363)
	%x364 = load i32, i32* @ii
	%x365 = add i32 0, 1
	%x366 = add i32 %x364, %x365
	store i32 %x366, i32* @ii
	br label %block97

block99:
	%x367 = load i32, i32* @i
	%x368 = load i32, i32* @ii
	%x369 = add i32 %x367, %x368
	%x370 = add i32 0, 1
	%x371 = sub i32 %x369, %x370
	store i32 %x371, i32* @i
	br label %block96

block96:
	br label %block82

block82:
	%x372 = load i32, i32* @i
	%x373 = add i32 0, 1
	%x374 = add i32 %x372, %x373
	store i32 %x374, i32* @i
	br label %block72

block74:
	%x375 = add i32 0, 1
	%x376 = getelementptr  [10000 x i32],  [10000 x i32]* @ints, i32 0, i32 %x375
	%x377 = load i32, i32* %x376
	call void @putint(i32 %x377)
	%x378 = add i32 0, 0
	ret i32 %x378
}