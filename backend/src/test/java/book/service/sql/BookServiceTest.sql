INSERT INTO book (id, title, author, outline, genre, record_count) VALUES
    (1, 'ノルウェイの森', '村上春樹', '十八年という歳月が...', '{YOUTH}', 0),
    (2, '博士の愛した数式', '小川洋子', 'ぼくの記憶は80分しか...', '{FANTASY}', 2),
    (3, '君の膵臓をたべたい', '住野よる', 'ある日、高校生の僕は病院で...', '{YOUTH}', 4),
    (4, '容疑者Xの献身', '東野圭吾', '天才数学者でありながら...', '{YOUTH}', 1)
;
SELECT SETVAL ('book_id_seq', '4');