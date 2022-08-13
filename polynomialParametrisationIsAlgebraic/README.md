# *Polynomial Parametrisation Is Algebraic*

## Description and Motivation

The problem discussed here is

**Problem.** 
<dl>
<dd>

Let $P_1, \dots, P_k \in R[x_1, \dots, x_n]$ be homogenous polynomials of the same degree $d$. Is there a nonzero polynomial $Q \in R[t_1, \dots, t_k]$ such that $Q(P_1, \dots, P_n)$ (which is $Q(P_1(x_1, \dots, x_n), \dots, P_n(x_1, \dots, x_n)) \in R[x_1, \dots, x_n]$) is zero polynomial? And what is its minimal degree $D$?

More precisely, for which tuples $(n, k, d)$ whichever ring $R$ and polynomials $P_1, \dots, P_k$ I take would there be such polynomial $Q$ and what is its minimal possible degree $D$?
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
<summary>Proof.</summary>

Let's denote $Q(P_1, \dots, P_n)$ as $Q(\overline{P})$. Then if $Q(\overline{P}) = 0$, $Q(\overline{P}) \cdot P_1^m = 0$, i.e. $Q'(\overline{P}) = 0$ for $Q' = Q \times t_1^m$. Also $\deg(Q') = \deg(Q) + m = D + m$. So for every natural $D' > D$ we have $m := D' - D > 0$, for which there is sought $Q'$ of degree $D + m = D'$.
</details>
</dd>
</dl>

**Idea 1.**
<dl>
<dd>

Let's fix homogenous polynomials $P_1, \dots, P_k \in R[x_1, \dots, x_n]$ of degree $d$ and for every homogenous polynomial $Q \in R[t_1, \dots, t_k]$ of degree $D$ calculate value
$$\varphi(Q) := Q(P_1, \dots, P_k).$$
It's easy to see that $\varphi$ is a linear map $R[t_1, \dots, t_k]_D \to R[x_1, \dots, x_n]_{Dd}$, where $R[\lambda_1, \dots, \lambda_m]_c$ denotes vector space of homogenous polynomials of variables $\lambda_1$, ..., $\lambda_m$ and degree $c$. Thus we want to determine if $\varphi$ has non-trivial kernel.

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

<!-- TODO: Дописать что (не может быть) известно про оставшиеся случаи. -->
</dd>
</dl>

## Experimental approach

<!-- TODO: Написать экспериментальный подход. -->

## Current Result

<!-- TODO: Описать готовые результаты. -->
