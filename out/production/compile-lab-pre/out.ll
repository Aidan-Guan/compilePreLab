declare void @putarray(i32,i32*)
@golbal = dso_local global [100 x i32] [ i32 1, i32 2, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0]
@x = dso_local global i32 0
@y = dso_local global i32 0
@z = dso_local global i32 0
define dso_local void @f(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = add i32 0, 3
	%x6 = add i32 0, 3
	%x7 = add i32 0, 3
	%x8 = alloca [3 x [3 x [3 x i32]]]
	%x9 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 0
	%x10 = add i32 0, 1
	store i32 %x10, i32* %x9
	%x11 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 1
	%x12 = add i32 0, 2
	store i32 %x12, i32* %x11
	%x13 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 2
	%x14 = add i32 0, 3
	store i32 %x14, i32* %x13
	%x15 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 0
	%x16 = add i32 0, 4
	store i32 %x16, i32* %x15
	%x17 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 1
	%x18 = add i32 0, 5
	store i32 %x18, i32* %x17
	%x19 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 2
	%x20 = add i32 0, 6
	store i32 %x20, i32* %x19
	%x21 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 0
	%x22 = add i32 0, 7
	store i32 %x22, i32* %x21
	%x23 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 1
	%x24 = add i32 0, 8
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 2
	%x26 = add i32 0, 9
	store i32 %x26, i32* %x25
	%x27 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 0
	%x28 = add i32 0, 1
	store i32 %x28, i32* %x27
	%x29 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 1
	%x30 = add i32 0, 2
	store i32 %x30, i32* %x29
	%x31 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 2
	%x32 = add i32 0, 3
	store i32 %x32, i32* %x31
	%x33 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 0
	%x34 = add i32 0, 4
	store i32 %x34, i32* %x33
	%x35 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 1
	%x36 = add i32 0, 5
	store i32 %x36, i32* %x35
	%x37 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 2
	%x38 = add i32 0, 6
	store i32 %x38, i32* %x37
	%x39 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 0
	%x40 = add i32 0, 7
	store i32 %x40, i32* %x39
	%x41 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 1
	%x42 = add i32 0, 8
	store i32 %x42, i32* %x41
	%x43 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 2
	%x44 = add i32 0, 9
	store i32 %x44, i32* %x43
	%x45 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 0
	%x46 = add i32 0, 1
	store i32 %x46, i32* %x45
	%x47 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 1
	%x48 = add i32 0, 2
	store i32 %x48, i32* %x47
	%x49 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 2
	%x50 = add i32 0, 3
	store i32 %x50, i32* %x49
	%x51 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 0
	%x52 = add i32 0, 4
	store i32 %x52, i32* %x51
	%x53 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 1
	%x54 = add i32 0, 5
	store i32 %x54, i32* %x53
	%x55 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 2
	%x56 = add i32 0, 6
	store i32 %x56, i32* %x55
	%x57 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 0
	%x58 = add i32 0, 7
	store i32 %x58, i32* %x57
	%x59 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 1
	%x60 = add i32 0, 8
	store i32 %x60, i32* %x59
	%x61 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 2
	%x62 = add i32 0, 9
	store i32 %x62, i32* %x61
	%x63 = add i32 0, 3
	%x64 = add i32 0, 3
	%x65 = add i32 0, 3
	%x66 = alloca [3 x [3 x [3 x i32]]]
	%x67 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 0
	%x68 = add i32 0, 1
	store i32 %x68, i32* %x67
	%x69 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 1
	%x70 = add i32 0, 2
	store i32 %x70, i32* %x69
	%x71 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 2
	%x72 = add i32 0, 3
	store i32 %x72, i32* %x71
	%x73 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 0
	%x74 = add i32 0, 4
	store i32 %x74, i32* %x73
	%x75 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 1
	%x76 = add i32 0, 5
	store i32 %x76, i32* %x75
	%x77 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 2
	%x78 = add i32 0, 6
	store i32 %x78, i32* %x77
	%x79 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 0
	%x80 = add i32 0, 7
	store i32 %x80, i32* %x79
	%x81 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 1
	%x82 = add i32 0, 8
	store i32 %x82, i32* %x81
	%x83 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 2
	%x84 = add i32 0, 9
	store i32 %x84, i32* %x83
	%x85 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 0
	%x86 = add i32 0, 1
	store i32 %x86, i32* %x85
	%x87 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 1
	%x88 = add i32 0, 2
	store i32 %x88, i32* %x87
	%x89 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 2
	%x90 = add i32 0, 3
	store i32 %x90, i32* %x89
	%x91 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 0
	%x92 = add i32 0, 4
	store i32 %x92, i32* %x91
	%x93 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 1
	%x94 = add i32 0, 5
	store i32 %x94, i32* %x93
	%x95 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 2
	%x96 = add i32 0, 6
	store i32 %x96, i32* %x95
	%x97 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 0
	%x98 = add i32 0, 7
	store i32 %x98, i32* %x97
	%x99 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 1
	%x100 = add i32 0, 8
	store i32 %x100, i32* %x99
	%x101 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 2
	%x102 = add i32 0, 9
	store i32 %x102, i32* %x101
	%x103 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 0
	%x104 = add i32 0, 1
	store i32 %x104, i32* %x103
	%x105 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 1
	%x106 = add i32 0, 2
	store i32 %x106, i32* %x105
	%x107 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 2
	%x108 = add i32 0, 3
	store i32 %x108, i32* %x107
	%x109 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 0
	%x110 = add i32 0, 4
	store i32 %x110, i32* %x109
	%x111 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 1
	%x112 = add i32 0, 5
	store i32 %x112, i32* %x111
	%x113 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 2
	%x114 = add i32 0, 6
	store i32 %x114, i32* %x113
	%x115 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 0
	%x116 = add i32 0, 7
	store i32 %x116, i32* %x115
	%x117 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 1
	%x118 = add i32 0, 8
	store i32 %x118, i32* %x117
	%x119 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 2
	%x120 = add i32 0, 9
	store i32 %x120, i32* %x119
	%x121 = add i32 0, 3
	%x122 = add i32 0, 3
	%x123 = add i32 0, 3
	%x124 = alloca [3 x [3 x [3 x i32]]]
	%x125 = alloca i32
	%x126 = alloca i32
	%x127 = alloca i32
	%x128 = alloca i32
	%x129 = add i32 0, 0
	store i32 %x129, i32* %x128
	%x130 = alloca i32
	%x131 = add i32 0, 0
	store i32 %x131, i32* %x130
	%x132 = alloca i32
	%x133 = add i32 0, 0
	store i32 %x133, i32* %x132
	br label %block0

block0:
	%x134 = load i32, i32* %x128
	%x135 = add i32 0, 3
	%x136 = icmp slt i32 %x134, %x135
	br i1 %x136, label %block1, label %block2

block1:
	%x137 = add i32 0, 0
	store i32 %x137, i32* %x130
	br label %block3

block3:
	%x138 = load i32, i32* %x130
	%x139 = add i32 0, 3
	%x140 = icmp slt i32 %x138, %x139
	br i1 %x140, label %block4, label %block5

block4:
	%x141 = add i32 0, 0
	store i32 %x141, i32* %x132
	br label %block6

block6:
	%x142 = load i32, i32* %x132
	%x143 = add i32 0, 3
	%x144 = icmp slt i32 %x142, %x143
	br i1 %x144, label %block7, label %block8

block7:
	%x145 = load i32, i32* %x128
	%x146 = load i32, i32* %x130
	%x147 = load i32, i32* %x132
	%x148 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x145, i32 %x146, i32 %x147
	%x149 = load i32, i32* %x128
	%x150 = load i32, i32* %x130
	%x151 = load i32, i32* %x132
	%x152 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 %x149, i32 %x150, i32 %x151
	%x153 = load i32, i32* %x152
	%x154 = load i32, i32* %x128
	%x155 = load i32, i32* %x130
	%x156 = load i32, i32* %x132
	%x157 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 %x154, i32 %x155, i32 %x156
	%x158 = load i32, i32* %x157
	%x159 = add i32 %x153, %x158
	store i32 %x159, i32* %x148
	%x160 = load i32, i32* %x132
	%x161 = add i32 0, 1
	%x162 = add i32 %x160, %x161
	store i32 %x162, i32* %x132
	br label %block6

block8:
	%x163 = load i32, i32* %x130
	%x164 = add i32 0, 1
	%x165 = add i32 %x163, %x164
	store i32 %x165, i32* %x130
	br label %block3

block5:
	%x166 = load i32, i32* %x128
	%x167 = add i32 0, 1
	%x168 = add i32 %x166, %x167
	store i32 %x168, i32* %x128
	br label %block0

block2:
	%x169 = add i32 0, 3
	%x170 = add i32 0, 0
	%x171 = add i32 0, 0
	%x172 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x170, i32 %x171, i32 0
	call void @putarray(i32 %x169, i32* %x172)
	%x173 = add i32 0, 3
	%x174 = add i32 0, 0
	%x175 = add i32 0, 1
	%x176 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x174, i32 %x175, i32 0
	call void @putarray(i32 %x173, i32* %x176)
	%x177 = add i32 0, 3
	%x178 = add i32 0, 0
	%x179 = add i32 0, 2
	%x180 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x178, i32 %x179, i32 0
	call void @putarray(i32 %x177, i32* %x180)
	%x181 = add i32 0, 3
	%x182 = add i32 0, 1
	%x183 = add i32 0, 0
	%x184 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x182, i32 %x183, i32 0
	call void @putarray(i32 %x181, i32* %x184)
	%x185 = add i32 0, 3
	%x186 = add i32 0, 1
	%x187 = add i32 0, 1
	%x188 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x186, i32 %x187, i32 0
	call void @putarray(i32 %x185, i32* %x188)
	%x189 = add i32 0, 3
	%x190 = add i32 0, 1
	%x191 = add i32 0, 2
	%x192 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x190, i32 %x191, i32 0
	call void @putarray(i32 %x189, i32* %x192)
	%x193 = add i32 0, 3
	%x194 = add i32 0, 2
	%x195 = add i32 0, 0
	%x196 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x194, i32 %x195, i32 0
	call void @putarray(i32 %x193, i32* %x196)
	%x197 = add i32 0, 3
	%x198 = add i32 0, 2
	%x199 = add i32 0, 1
	%x200 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x198, i32 %x199, i32 0
	call void @putarray(i32 %x197, i32* %x200)
	%x201 = add i32 0, 3
	%x202 = add i32 0, 2
	%x203 = add i32 0, 2
	%x204 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x202, i32 %x203, i32 0
	call void @putarray(i32 %x201, i32* %x204)
	ret void
}
define dso_local i32 @main(){
	%x1 = getelementptr  [100 x i32],  [100 x i32]*@golbal, i32 0, i32 0
	%x2 = add i32 0, 16
	call void @f(i32* %x1, i32 %x2)
	%x3 = add i32 0, 0
	ret i32 %x3
}