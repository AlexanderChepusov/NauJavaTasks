--
-- PostgreSQL database dump
--

\restrict QbHKtrrC927mR3g95tTLiiflGvN9oeNq64wqqdC4vooWPKz1K7cgYZXKWTvjK2c

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

-- Started on 2026-11-11 18:44:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16491)
-- Name: citext; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION citext; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';


--
-- TOC entry 927 (class 1247 OID 16456)
-- Name: operation_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.operation_type AS ENUM (
    'income',
    'payment'
);


ALTER TYPE public.operation_type OWNER TO postgres;

--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 927
-- Name: TYPE operation_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE public.operation_type IS 'Тип операции: 
 - income - приход или накопление;
 - payment - платеж, расход
';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 16395)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    id integer NOT NULL,
    name text CONSTRAINT "accounts_accountName_not_null" NOT NULL,
    type character varying(20),
    expiration_date timestamp with time zone DEFAULT now()
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 5039 (class 0 OID 0)
-- Dependencies: 221
-- Name: TABLE accounts; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.accounts IS 'банковские счета';


--
-- TOC entry 228 (class 1259 OID 16448)
-- Name: accounts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.accounts ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.accounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 16401)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    name public.citext CONSTRAINT "categories_categoryName_not_null" NOT NULL,
    percentage integer,
    type character varying(10) DEFAULT 'payment'::character varying
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 5040 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE categories; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.categories IS 'Категории покупок и накоплений';


--
-- TOC entry 223 (class 1259 OID 16405)
-- Name: marketplaces; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marketplaces (
    id integer NOT NULL,
    name character varying(30) CONSTRAINT "Marketplaces_mpName_not_null" NOT NULL,
    address character varying(100) CONSTRAINT "Marketplaces_maAddress_not_null" NOT NULL,
    working_time integer DEFAULT 22
);


ALTER TABLE public.marketplaces OWNER TO postgres;

--
-- TOC entry 5041 (class 0 OID 0)
-- Dependencies: 223
-- Name: TABLE marketplaces; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.marketplaces IS 'Любимые магазины';


--
-- TOC entry 226 (class 1259 OID 16445)
-- Name: marketplaces_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.marketplaces ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.marketplaces_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 16414)
-- Name: operations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.operations (
    id bigint NOT NULL,
    type character varying(10) DEFAULT 'payment'::character varying NOT NULL,
    amount numeric(10,2),
    category_name public.citext,
    account_id integer,
    user_id integer,
    date_time timestamp with time zone DEFAULT now(),
    marketplace_id integer
);


ALTER TABLE public.operations OWNER TO postgres;

--
-- TOC entry 5042 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE operations; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.operations IS 'Список всех транзацкций-операций';


--
-- TOC entry 227 (class 1259 OID 16446)
-- Name: operations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.operations ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.operations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 16631)
-- Name: reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reports (
    id integer NOT NULL,
    status character varying(10) NOT NULL,
    content character varying
);


ALTER TABLE public.reports OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16640)
-- Name: reports_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reports ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reports_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 16432)
-- Name: user_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_accounts (
    user_id integer NOT NULL,
    account_id integer,
    account_number character varying(20) NOT NULL,
    comment character varying(100)
);


ALTER TABLE public.user_accounts OWNER TO postgres;

--
-- TOC entry 5043 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE user_accounts; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.user_accounts IS 'Сводная таблица для сопоставления пользователей и их счетов';


--
-- TOC entry 220 (class 1259 OID 16389)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer CONSTRAINT users_user_id_not_null NOT NULL,
    first_name character varying(50) CONSTRAINT users_name_not_null NOT NULL,
    last_name character varying(50),
    age integer,
    login character varying(20),
    password character varying,
    roles character varying[]
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 5044 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE users; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.users IS 'пользователи в приложении';


--
-- TOC entry 229 (class 1259 OID 16449)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 5021 (class 0 OID 16395)
-- Dependencies: 221
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (id, name, type, expiration_date) FROM stdin;
1	TinkovBank	\N	2026-10-15 15:37:22.813758+05
2	UBRR	\N	2026-10-15 15:37:22.813758+05
3	PSB	\N	2026-10-15 15:37:22.813758+05
4	LokoBank	\N	2026-10-15 15:37:22.813758+05
5	LokoBank_VKLAD	\N	2026-10-15 15:37:22.813758+05
6	REDbank	\N	2026-10-15 15:37:22.813758+05
7	REDbank_vklad	\N	2026-10-15 15:37:22.813758+05
8	REDbank_nakopit_schet	\N	2026-10-15 15:37:22.813758+05
\.


--
-- TOC entry 5022 (class 0 OID 16401)
-- Dependencies: 222
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (name, percentage, type) FROM stdin;
10% for youself	\N	\N
cafe&Restaurants	5	\N
cakes	\N	\N
cinema	\N	\N
education	\N	\N
food	\N	\N
for journey	\N	\N
gasoline	3	\N
new apartment	\N	\N
new car	\N	\N
theatre	20	\N
utilities	\N	\N
vacations	\N	\N
\.


--
-- TOC entry 5023 (class 0 OID 16405)
-- Dependencies: 223
-- Data for Name: marketplaces; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marketplaces (id, name, address, working_time) FROM stdin;
7	Magnit	Kuibisheva 205	22
8	Magnit	Tavatuiskaya 14	22
9	Perekrestok	Bessarabskaya	22
10	Perekrestok	Passaj	22
11	Yabloko	Vasilieva 16	22
12	Zhizhn-Mart	somewhere	22
13	5ka	Academ	22
14	5ka	Centr	22
15	5ka	Uralmash	22
16	5ka	Vokzal	22
\.


--
-- TOC entry 5024 (class 0 OID 16414)
-- Dependencies: 224
-- Data for Name: operations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.operations (id, type, amount, category_name, account_id, user_id, date_time, marketplace_id) FROM stdin;
3	payment	800.00	cinema	1	2	2026-10-15 15:06:58.878086+05	10
4	payment	7200.00	utilities	1	2	2026-10-15 15:10:25.115421+05	10
6	payment	2541.00	cafe&restaurants	6	3	2026-10-15 15:13:09.963136+05	11
5	payment	6113.34	utilities	6	3	2026-10-15 15:12:13.366703+05	20
7	payment	2741.00	gasoline	6	3	2026-10-15 15:14:10.63847+05	25
8	payment	2256.91	gasoline	6	3	2026-10-15 15:14:25.177525+05	25
13	payment	1000.00	cakes	6	3	2026-10-15 15:15:29.283794+05	16
14	payment	1500.00	cakes	6	3	2026-10-15 15:15:34.211537+05	16
15	payment	3500.00	cakes	6	3	2026-10-15 15:15:37.658+05	16
16	payment	3500.00	cakes	6	3	2026-10-15 15:15:50.441125+05	13
18	income	5000.00	new apartment	7	3	2026-10-15 15:17:04.712129+05	1
19	income	7000.00	new car	7	3	2026-10-15 15:17:13.489549+05	1
20	income	2000.00	new car	8	3	2026-10-15 15:17:25.978843+05	1
21	income	3500.00	new apartment	8	3	2026-10-15 15:17:37.559132+05	1
22	income	16000.00	new apartment	7	3	2026-10-15 15:17:46.417619+05	1
23	income	10000.00	new car	7	3	2026-10-15 15:17:55.412784+05	1
24	income	25000.00	for journey	8	3	2026-10-15 15:18:16.033738+05	1
25	income	2000.00	for journey	8	3	2026-10-15 15:18:20.678085+05	1
26	income	1000.00	for journey	8	3	2026-10-15 15:18:24.391164+05	1
9	payment	1356.18	food	6	3	2026-10-15 15:15:04.141091+05	15
12	payment	5546.18	food	6	3	2026-10-15 15:15:18.446093+05	16
1	payment	3568.12	food	1	2	2026-10-26 19:13:05.528772+05	7
11	payment	546.18	food	6	3	2026-10-15 15:15:11.564484+05	15
17	payment	954.00	food	6	3	2026-10-15 15:16:00.6095+05	13
2	payment	5468.00	Food	1	2	2026-10-25 00:00:00+05	8
10	payment	56.18	Food	6	3	2026-10-15 15:15:08.358391+05	15
\.


--
-- TOC entry 5030 (class 0 OID 16631)
-- Dependencies: 230
-- Data for Name: reports; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reports (id, status, content) FROM stdin;
\.


--
-- TOC entry 5025 (class 0 OID 16432)
-- Dependencies: 225
-- Data for Name: user_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_accounts (user_id, account_id, account_number, comment) FROM stdin;
1	3	psb-1	\N
1	3	psb-2	\N
1	2	ubrr-1	\N
3	7	r-2	\N
3	6	r-1	\N
3	8	r-3	\N
3	1	t-1	\N
\.


--
-- TOC entry 5020 (class 0 OID 16389)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, first_name, last_name, age, login, password, roles) FROM stdin;
16	Snoop	Doggg	57	Dog	$2a$10$6ba1VHGoA73wsWPAfMi5EODeWZmsRKCvY7aId4eIRzXykIWJbkp8.	{USER}
14	Administrator	Admin	7	admin	$2a$10$M6DuFoZYbZTR2dJ8Hku33.81GoXniy8s2NAaGiP62NgpfbeUL2Ady	{ADMIN}
9	Samanta	Campbell	29	Samanta	$2a$10$Cl4wXwsUfSj/nX376VZFDuNWSjBVMsM9RwrSEPOWMr5ny35EJ.CGu	{USER}
1	Anna	Karenina	30	Anny	$2a$10$iKo95fZLBVGIiAIZWsG5duu8Q/NqJ3hjweacsnNZckY0sZye29A3i	{USER}
3	James	Bond	61	007	$2a$10$Uh6nyBD6z1GNS0ZbNrdKZeHgbAD.5af.UpvffwYlu0.sCA8INH0Vi	{USER}
4	Nick	Fury	75	Fury	$2a$10$rtUZU8F1qCB7Fq53JfrBe.em.bcW3GHTdfKN8lgCYcald3742DcmK	{USER}
5	Alex	Chas	36	Chas	$2a$10$n4UVXT.K.d275wZ3X9pOrOGKhDQjtSzrE/es.pvdElv0rsjc8B.aK	{USER}
2	Ivan	Ivanov	25	Ivan	$2a$10$/MAWtCkCP9MHlbfLk0HL8OMlRbMQo0DP4AMEfQiLZ666EVTdR.YDK	{USER}
12	Alex	Chas	36	chas90	$2a$10$XaQS4Y0uHpziwftMg4qyquoPsy41wqLn9ymQucMHCnPtyEQOmQhfm	{ADMIN,USER}
7	Jane	Brown	35	Jane	$2a$10$Im69aZw1mpCY5H9DDZCh5e1EFEia3lv8Ae3aUekQyJht2S/4QxqYK	{USER}
11	Sonya	Blade	46	Sonya	$2a$10$0i6XzUjbwOPMzHNW3a/OFeU1m2rnzg7ZEMZXvrRMODueLKB0bjWiG	{USER}
15	Mike	Modano	54	Mike	$2a$10$RfzB7ddVffY8lJrqfmQ01ej31xfoaC2ElFDaF0qwSEdSQBhcIHPE.	{USER}
13	Peter	Parker	25	spidey	$2a$10$gq1Wdu7wbk6m08S5eskpBO8Lqthl1uZ6M1f9hnIFjcrE25ALnX0fq	{USER}
17	Ivan	Ivanov	31	Dava	$2a$10$W3c2L8FSPeOmmPdmR7RyIeu4IVresI7RpNC.BFqOfjnrYHamFhUyO	{USER}
\.


--
-- TOC entry 5045 (class 0 OID 0)
-- Dependencies: 228
-- Name: accounts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accounts_id_seq', 8, true);


--
-- TOC entry 5046 (class 0 OID 0)
-- Dependencies: 226
-- Name: marketplaces_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marketplaces_id_seq', 16, true);


--
-- TOC entry 5047 (class 0 OID 0)
-- Dependencies: 227
-- Name: operations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.operations_id_seq', 26, true);


--
-- TOC entry 5048 (class 0 OID 0)
-- Dependencies: 231
-- Name: reports_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reports_id_seq', 1, false);


--
-- TOC entry 5049 (class 0 OID 0)
-- Dependencies: 229
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 17, true);


--
-- TOC entry 4856 (class 2606 OID 16431)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- TOC entry 4858 (class 2606 OID 16607)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (name);


--
-- TOC entry 4860 (class 2606 OID 16428)
-- Name: marketplaces marketplaces_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marketplaces
    ADD CONSTRAINT marketplaces_pkey PRIMARY KEY (id);


--
-- TOC entry 4862 (class 2606 OID 16420)
-- Name: operations operations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT operations_pkey PRIMARY KEY (id);


--
-- TOC entry 4866 (class 2606 OID 16639)
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


--
-- TOC entry 4864 (class 2606 OID 16454)
-- Name: user_accounts user_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_accounts
    ADD CONSTRAINT user_accounts_pkey PRIMARY KEY (account_number);


--
-- TOC entry 4852 (class 2606 OID 16630)
-- Name: users users_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_unique UNIQUE (login);


--
-- TOC entry 4854 (class 2606 OID 16394)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4867 (class 2606 OID 16476)
-- Name: operations fk_account_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT fk_account_id FOREIGN KEY (id) REFERENCES public.accounts(id) NOT VALID;


--
-- TOC entry 4871 (class 2606 OID 16440)
-- Name: user_accounts fk_account_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_accounts
    ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES public.accounts(id) NOT VALID;


--
-- TOC entry 4868 (class 2606 OID 16611)
-- Name: operations fk_category_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT fk_category_name FOREIGN KEY (category_name) REFERENCES public.categories(name);


--
-- TOC entry 4869 (class 2606 OID 16481)
-- Name: operations fk_marketplace_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT fk_marketplace_id FOREIGN KEY (id) REFERENCES public.marketplaces(id) NOT VALID;


--
-- TOC entry 4870 (class 2606 OID 16421)
-- Name: operations fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 5050 (class 0 OID 0)
-- Dependencies: 4870
-- Name: CONSTRAINT fk_user_id ON operations; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT fk_user_id ON public.operations IS 'Пользователь, совершивший покупку/накопление';


--
-- TOC entry 4872 (class 2606 OID 16435)
-- Name: user_accounts fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_accounts
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;


-- Completed on 2026-11-11 18:44:16

--
-- PostgreSQL database dump complete
--

\unrestrict QbHKtrrC927mR3g95tTLiiflGvN9oeNq64wqqdC4vooWPKz1K7cgYZXKWTvjK2c

