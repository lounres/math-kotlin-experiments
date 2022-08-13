# *Polynomial Parametrisation Is Algebraic*

## Problem description and Motivation

The problem discussed here is

**Problem.** 
<dl>
<dd>

Let $P_1, \dots, P_k \in R[x_1, \dots, x_n]$ be homogenous polynomials of the same degree $d$. Is there a nonzero polynomial $Q \in R[t_1, \dots, t_k]$ such that $Q(P_1, \dots, P_n)$ (which is $Q(P_1(x_1, \dots, x_n), \dots, P_n(x_1, \dots, x_n)) \in R[x_1, \dots, x_n]$) is zero polynomial? And what is its minimal degree $D$?

More precisely, for which tuples $(n, k, d) \in (\mathbb{N} \setminus \{0\})^3$ whichever ring $R$ and polynomials $P_1, \dots, P_k$ I take would there be such polynomial $Q$ and what is its minimal possible degree $D$?

Let's denote the sought minimal value $D(n, k, d)$.
</dd>
</dl>

*Motivation for the problem*:

<dl>
<dd>

Let there be any reason some curve on projective plane that is polynomially parametrised (in homogenous coordinates, obviously). Does this curve lies on some algebraic curve? That's a question we want to answer. It's also similar to common questions of (and actually comes from) olympiad flavoured projective geometry.

Then from algebraic point of view it means that there are some homogenous polynomials $P_1, P_2, P_3 \in R[x_1, x_2]$ (so $(P_1, P_2, P_3)$ is a parametrising map) and we want to determine if there is non-trivial homogenous polynomial $Q \in R[t_1, t_2, t_3]$ such that
$$Q(P_1(x_1, x_2), P_2(x_1, x_2), P_3(x_1, x_2)) = 0$$
for any $(x_1:x_2) \in RP^2$ (which is equivalent to lying on curve $Q = 0$). While $\mathop{\mathrm{char}}(R) = 0$ the last question is equivalent to polynomial equality (instead of functional equality). Here we have case $n = 2$, $k = 3$.
</dd>
</dl>

There are some formal remarks.

## Thoughts

**Remark 1.** 
<dl>
<dd>

If there exists such sought polynomial $Q$ and its degree is $D$, then there is such sought polynomial $Q'$ of any higher degree. That's why we can ask about not all possible $D$, but just about minimal $D$.

<details>
<summary><em>Proof.</em></summary>

Let's denote $Q(P_1, \dots, P_n)$ as $Q(\overline{P})$. Then if $Q(\overline{P}) = 0$, $Q(\overline{P}) \cdot P_1^m = 0$, i.e. $Q'(\overline{P}) = 0$ for $Q' = Q \times t_1^m$. Also $\deg(Q') = \deg(Q) + m = D + m$. So for every natural $D' > D$ we have $m := D' - D > 0$, for which there is sought $Q'$ of degree $D + m = D'$.

$$\,\tag*{$\Box$}$$
</details>
</dd>
</dl>

### Idea 1.

Let's fix homogenous polynomials $P_1, \dots, P_k \in R[x_1, \dots, x_n]$ of degree $d$ and for every homogenous polynomial $Q \in R[t_1, \dots, t_k]$ of degree $D$ calculate value
$$\varphi(Q) := Q(P_1, \dots, P_k).$$
It's easy to see that $\varphi$ is a linear map $R[t_1, \dots, t_k]_D \to R[x_1, \dots, x_n]_{Dd}$, where $R[\lambda_1, \dots, \lambda_m]_c$ denotes vector space of homogenous polynomials of variables $\lambda_1$, ..., $\lambda_m$ and degree $c$. Thus we want to determine if $\varphi$ has non-trivial kernel.

**Remark 2.** 
<dl>
<dd>

[//]: # (TODO)

TODO: Здесь должна быть заметка про то, как можно вместо $R$ рассматривать $\mathbb{Z}_m[s_1, \dots]$, а затем и $\mathbb{Z}[s_1, \dots]$.

<!-- Consider a ring homomorphism $\alpha: \mathbb{Z} \to R$ and subring $R' := \mathop{\mathrm{range}}(\alpha)$ of $R$. Let $B = \{b_i\}_{i \in I}$ be set of all monic monomials from $R[x_1, \dots, x_n]_d$. Obviously $B$ is basis of $R[x_1, \dots, x_n]_d$. Then any $P_g \in R[x_1, \dots, x_n]_d$ can be represented as
$$P_g = \sum_{i \in I} r_{g, i} b_i$$
for some $r_i \in R$. It's also obvious that $B$ lies in $R'[x_1, \dots, x_n]_d$ and is its basis.

Let $S$ be extension of $R'$ with formal symbols $s_{g, i}$ for each $g \in \{1; \dots; k\}$ and $i \in I$. Then there is "evaluating" homomorphism $\pi: S \to R$ that maps each element of $R'$ to itself and each $s_{g, i}$ to $r_{g, i}$. Also there is its extension $\pi': S[x_1, \dots, x_n] \to R[x_1, \dots, x_n]$ that maps each $x_i$ to itself. Let
$$P'_g := \sum_{i \in I} s_{g, i} b_i \in S[x_1, \dots, x_n]_d$$
Then obviously $\pi'(P'_g) = P_g$.

Let $C = \{c_j\}_{j \in J}$ and $A = \{a_h\}_{h \in H}$ be monic-monomial bases of $R[t_1, \dots, t_k]_D$ and $R[x_1, \dots, x_n]_{Dd}$ respectively. (Obviously we can freely use $R'$ or $S$ instead of $R$ in the definition.) Then let's decompose each $c_j$ as
$$c_j(P'_1, \dots, P'_k) = \sum_{h \in H} \eta_{j, h} a_h \in S[x_1, \dots, $$

Let's temporarily denote $D(n, k, d)$ in context of $R$ as $D_R(n, k, d)$. Then $D_R(n, k, d) \leqslant D_\mathbb{Z}(n, k, d)$. -->

<!-- <details>
<summary><em>Proof.</em></summary>

Let's denote $Q(P_1, \dots, P_n)$ as $Q(\overline{P})$. Then if $Q(\overline{P}) = 0$, $Q(\overline{P}) \cdot P_1^m = 0$, i.e. $Q'(\overline{P}) = 0$ for $Q' = Q \times t_1^m$. Also $\deg(Q') = \deg(Q) + m = D + m$. So for every natural $D' > D$ we have $m := D' - D > 0$, for which there is sought $Q'$ of degree $D + m = D'$.

$$\,\tag*{$\Box$}$$
</details> -->
</dd>
</dl>

(*Note that this is equivalent statement! The following part describes only sufficient condition of $D(n, k, d) < \infty$ and, thus, upper bound of $D(n, k, d)$.*)

The most obvious way to prove that there kernel of linear map is not trivial is to show that domain dimension is strictly higher than codomain dimension. So let's calculate the dimensions.

Obviously,
$$
\begin{align*}
    \dim R[\lambda_1, \dots, \lambda_m]_c
    &= \#\left\{(b_1, \dots, b_m) \left|\, b_1, \dots, b_m \in \mathbb{N} \wedge \sum_{i=1}^m b_i = c \right.\right\}\\
    &= \left(\!\!\binom{m}{c}\!\!\right)
    = \binom{m+c-1}{c}
    = \binom{m+c-1}{m-1}.
\end{align*}
$$
So the question is for what minimal $D$
$$
\binom{k+D-1}{k-1} > \binom{n+Dd-1}{n-1}.
$$

Obviously, $\deg_D \binom{k+D-1}{k-1} = k-1$ and $\deg_D \binom{n+Dd-1}{n-1} = n-1$ (here $\deg_D$ is degree as of polynomials of $D$). Hence, if $n < k$ then such $D$ exists for every $d$.

**Lemma 1.**
<dl>
<dd>

For any $n, k, d \in \mathbb{N} \setminus \{0\}$ and $D \in \mathbb{N}$ such that $k \leqslant n$
$$\binom{D+k-1}{k-1} \leqslant \binom{dD+n-1}{n-1}.$$

<details>
<summary><em>Proof.</em></summary>

Consider partial order $\preccurlyeq$ on $M := \{(n, k, D) \in \mathbb{N}^3 \mid 0 < k \leqslant n\}$ that is reflexive and transitive closure of relation
$$(n, k, D) \preccurlyeq (n, k, D+1) \qquad \wedge \qquad (n, k, D) \preccurlyeq (n+1, k+1, D).$$
Obviously, $\preccurlyeq$ is well-founded.

Consider statement
$$P(n, k, D) := \forall d \in \mathbb{N} \setminus \{0\} \quad \binom{D+k-1}{k-1} \leqslant \binom{dD+n-1}{n-1}.$$
Let's show that $P(n+1, k+1, D+1)$ follows from $P(n, k, D+1)$ and $P(n+1, k+1, D)$ and also $P(n, 1, D)$ and $P(n, k, 0)$ are true. Hence, by transfinite induction $P(x)$ is true for any $x \in M$.

$$\binom{D+1-1}{1-1} = 1 \leqslant \binom{Dd+n-1}{n-1}$$
Thus, $P(n, 1, D)$ is true.

$$\binom{0+k-1}{k-1} = 1 = \binom{0 \cdot d+n-1}{n-1}$$
Thus, $P(n, k, 0)$ is true.

Assuming $P(n+1, k+1, D)$ and $P(n, k, D+1)$
$$
    \begin{align*}
        \binom{(D+1)+(k+1)-1}{(k+1)-1}
        &= \binom{D+(k+1)-1}{(k+1)-1} + \binom{(D+1)+k-1}{k-1}\\
        &\leqslant \binom{Dd+(n+1)-1}{(n+1)-1} + \binom{(D+1)d+n-1}{n-1}\\
        &\leqslant \binom{Dd+(n+1)-1}{(n+1)-1} + \sum_{i=1}^{d} \binom{Dd+i+n-1}{n-1}\\
        &= \binom{(D+1)d+(n+1)-1}{(n+1)-1}\\
    \end{align*}
$$
Thus, $P(n+1, k+1, D+1)$ follows from $P(n+1, k+1, D)$ and $P(n, k, D+1)$.

$$\,\tag*{$\Box$}$$
</details>
</dd>
</dl>

### Idea 2.

Let $n \geqslant k$ and $P_i = x_i^d$. Then it is obvious that for any non-zero polynomial $Q$ value $Q(\overline{P})$ is not zero. Thus for $n \geqslant k$ we have $D(n, k, d) = \infty$.

### Idea 3.

**Lemma 2.**
<dl>
<dd>

For any $k \geqslant 2$
$$D(1, k, d) = 1$$

<details>
<summary><em>Proof.</em></summary>

The $P_1$, ..., $P_k$ are $k$ polynomials of only variable $x_1$ and same degree $d$. Then obviously, $P_i = x_1^d$, so $Q = t_1 - t_2$ is enough (because $Q(\overline{P}) = P_1 - P_2 = x_1^d - x_1^d = 0$). Thus, $D = 1$.

$$\,\tag*{$\Box$}$$
</details>
</dd>
</dl>

<!-- **Lemma 2.**
<dl>
<dd>

1. $D(2, 3, d) \leqslant 2d - 2$
1. $D(2, 4, d) < \sqrt{6d-2} - 2$

<details>
<summary><em>Proof.</em></summary>



$$\,\tag*{$\Box$}$$
</details>
</dd>
</dl> -->

## Experimental approach

[//]: # (TODO)

TODO: Описание (конечного детерминированного) алгоритма проверки четвёрки $(n, k, d, D)$.

### Approach 1.

Based on idea 1 for any polynomials 

## Current Result

Known values of $D$ depending on $n$ and $k$:
|${}_n\! \diagdown\! {}^k$| $1$      | $2$      | $3$      | $4$      | $5$      | $6$      | $7$      | $8$      |
| :---------------------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| $1$                     | $\infty$ | $1$      | $1$      | $1$      | $1$      | $1$      | $1$      | $1$      |
| $2$                     | $\infty$ | $\infty$ |
| $3$                     | $\infty$ | $\infty$ | $\infty$ |
| $4$                     | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| $5$                     | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| $6$                     | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| $7$                     | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| $8$                     | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |

<!-- TODO: Описать готовые результаты. -->
