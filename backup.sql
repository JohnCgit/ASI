PGDMP     (    '                y        
   CardMaster    13.2    13.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16612 
   CardMaster    DATABASE     h   CREATE DATABASE "CardMaster" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_France.1252';
    DROP DATABASE "CardMaster";
                postgres    false            ?            1259    16668    card    TABLE     %  CREATE TABLE public.card (
    id integer NOT NULL,
    affinity integer,
    energy integer NOT NULL,
    family character varying(255),
    hp integer NOT NULL,
    img_url character varying(255),
    name character varying(255),
    price integer NOT NULL,
    strength integer NOT NULL
);
    DROP TABLE public.card;
       public         heap    postgres    false            ?            1259    16629    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            ?            1259    16663    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    id_card_player1 integer NOT NULL,
    id_card_player2 integer NOT NULL,
    id_player1 integer NOT NULL,
    id_player2 integer NOT NULL,
    mise integer NOT NULL,
    message character varying(1024)
);
    DROP TABLE public.room;
       public         heap    postgres    false            ?            1259    16658    transaction    TABLE     i   CREATE TABLE public.transaction (
    id integer NOT NULL,
    card_id integer,
    seller_id integer
);
    DROP TABLE public.transaction;
       public         heap    postgres    false            ?            1259    16642    utilisateur    TABLE     ?   CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    money integer NOT NULL,
    name character varying(255),
    password character varying(255),
    surname character varying(255)
);
    DROP TABLE public.utilisateur;
       public         heap    postgres    false            ?            1259    16650    utilisateur_collection    TABLE     l   CREATE TABLE public.utilisateur_collection (
    utilisateur_id integer NOT NULL,
    collection integer
);
 *   DROP TABLE public.utilisateur_collection;
       public         heap    postgres    false            ?          0    16668    card 
   TABLE DATA           `   COPY public.card (id, affinity, energy, family, hp, img_url, name, price, strength) FROM stdin;
    public          postgres    false    205   '       ?          0    16663    room 
   TABLE DATA           k   COPY public.room (id, id_card_player1, id_card_player2, id_player1, id_player2, mise, message) FROM stdin;
    public          postgres    false    204   ?       ?          0    16658    transaction 
   TABLE DATA           =   COPY public.transaction (id, card_id, seller_id) FROM stdin;
    public          postgres    false    203   ?       ?          0    16642    utilisateur 
   TABLE DATA           I   COPY public.utilisateur (id, money, name, password, surname) FROM stdin;
    public          postgres    false    201          ?          0    16650    utilisateur_collection 
   TABLE DATA           L   COPY public.utilisateur_collection (utilisateur_id, collection) FROM stdin;
    public          postgres    false    202   ?       ?           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 134, true);
          public          postgres    false    200            <           2606    16675    card card_pkey1 
   CONSTRAINT     M   ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pkey1 PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.card DROP CONSTRAINT card_pkey1;
       public            postgres    false    205            :           2606    16667    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    204            8           2606    16662    transaction transaction_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public            postgres    false    203            6           2606    16649    utilisateur utilisateur_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT utilisateur_pkey;
       public            postgres    false    201            =           2606    16653 2   utilisateur_collection fk9n7dgjfu26er7op3khig7ga0h    FK CONSTRAINT     ?   ALTER TABLE ONLY public.utilisateur_collection
    ADD CONSTRAINT fk9n7dgjfu26er7op3khig7ga0h FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);
 \   ALTER TABLE ONLY public.utilisateur_collection DROP CONSTRAINT fk9n7dgjfu26er7op3khig7ga0h;
       public          postgres    false    201    202    2870            ?   ?   x???M? ???1???????PLZ ?%??5ҟ?}W??/?}?[? ~?^?R?N???iV~?t?w??a?.i??n^]&&?KƑY???b?4?+,jHQA?P#?G?????m?? ?)}?E\??
???t?_?X ??ܵ?,??X3?ȍ2?w[j$?)!?*1],      ?      x?????? ? ?      ?      x?????? ? ?      ?   W   x?M?;? 뷇1????ņDJ?a1^?(?4/y3?G?V??	?9??p?#X?4.??
?fŞ??3??GYaWy?ʨAC+?p???/%U      ?   .   x?3?4?2???@l?A??m????( #???*a?.???? ?x?     